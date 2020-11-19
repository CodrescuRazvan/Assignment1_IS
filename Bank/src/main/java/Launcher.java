import controller.user.LoginController;
import factory.ComponentFactory;
import view.user.LoginView;

public class Launcher {

    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance(false);
        new LoginController(new LoginView(), componentFactory);
    }

}