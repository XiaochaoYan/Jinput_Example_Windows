import net.java.games.input.*;

public class InputExample {
    public static void main(String[] args) {
        // Get the default controller environment
        ControllerEnvironment controllerEnvironment = ControllerEnvironment.getDefaultEnvironment();
        Controller[] controllers = controllerEnvironment.getControllers();

        // Print information about each controller
        for (Controller controller : controllers) {
            System.out.println("Controller: " + controller.getName() + " - Type: " + controller.getType());
            Component[] components = controller.getComponents();
            for (Component component : components) {
                System.out.println("    Component: " + component.getName() + " - Identifier: " + component.getIdentifier());
            }
        }

        // Start capturing input events from the first gamepad found
        for (Controller controller : controllers) {
            if (controller.getType() == Controller.Type.GAMEPAD) {
                startControllerInput(controller);
                break;
            }
        }
        
        while (true){
            
        }
        
        
    }

    private static void startControllerInput(Controller controller) {
        // Create a new thread to continuously poll controller events
        new Thread(() -> {
            controller.poll();
            EventQueue eventQueue = controller.getEventQueue();
            Event event = new Event();

            while (true) {
                if (eventQueue.getNextEvent(event)) {
                    // Print event details
                    Component component = event.getComponent();
                    float value = event.getValue();
                    System.out.println("Event: Component=" + component.getName() + ", Value=" + value);
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
