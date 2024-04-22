import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Mouse;

public class JinputMouse {
    public static void main(String[] args) {
        // Get the default controller environment
        ControllerEnvironment controllerEnvironment = ControllerEnvironment.getDefaultEnvironment();
        Controller[] controllers = controllerEnvironment.getControllers();

        // Find the mouse controller
        Mouse mouse = null;
        for (Controller controller : controllers) {
            if (controller.getType() == Controller.Type.MOUSE) {
                mouse = (Mouse) controller;
                break;
            }
        }

        System.out.println("Mouse Created " + mouse.getName());

        if (mouse != null) {
            // Poll the controller to update its state
            mouse.poll();

            // Get the mouse movement components
            Component xComponent = mouse.getX();
            Component yComponent = mouse.getY();
            
            while (true) {
                // Poll the controller to update its state
                mouse.poll();

                // Get the current X and Y values of mouse movement
                float deltaX = xComponent.getPollData();
                float deltaY = yComponent.getPollData();

                // Print out the mouse movement
                System.out.println("Mouse Movement - Delta X: " + deltaX + ", Delta Y: " + deltaY);
                System.out.println("Event Queue ");

                try {
                    Thread.sleep(10); // Sleep for a short duration to avoid consuming too much CPU
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No mouse controller found.");
        }
    }
}