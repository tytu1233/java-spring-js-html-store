package pl.britenet.consoleapp;

import pl.britenet.consoleapp.obj.command.*;
import pl.britenet.consoleapp.service.*;

import java.net.ConnectException;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static boolean IS_ON = true;

    public static void main(String[] args) {
        DatabaseService databaseService = null;
        try {
            databaseService = new DatabaseService();
        } catch (ConnectException e) {
            System.out.println(e.getMessage());
            IS_ON = false;
        }

        UserCartService userCartService = new UserCartService(databaseService);
        UserOrderProductService userOrderProductService = new UserOrderProductService(databaseService);
        HigherQuantityService higherQuantityService = new HigherQuantityService(databaseService);
        UserOrdersService userOrdersService = new UserOrdersService(databaseService);
        ReturnedOrdersService returnedOrdersService = new ReturnedOrdersService(databaseService);
        SoldProductService soldProductService = new SoldProductService(databaseService);
        OrderProductService orderProductService = new OrderProductService(databaseService);
        CartProductService cartProductService = new CartProductService(databaseService);
        CartService cartService = new CartService(databaseService);
        OrderService orderService = new OrderService(databaseService);
        UserService userService = new UserService(databaseService);
        ProductService productService = new ProductService(databaseService);
        CommandService commandService = new CommandService();
        commandService.registerCommand(new HelpCommand(commandService));
        commandService.registerCommand(new QuitCommand());
        commandService.registerCommand(new InsertProductCommand(productService));
        commandService.registerCommand(new UpdateProductCommand(productService));
        commandService.registerCommand(new DeleteProductCommand(productService));
        commandService.registerCommand(new GetProductCommand(productService));
        commandService.registerCommand(new GetAllProductCommand(productService));
        commandService.registerCommand(new InsertUserCommand(userService));
        commandService.registerCommand(new UpdateUserCommand(userService));
        commandService.registerCommand(new DeleteUserCommand(userService));
        commandService.registerCommand(new GetUserCommand(userService));
        commandService.registerCommand(new GetAllUserCommand(userService));
        commandService.registerCommand(new InsertOrderCommand(orderService));
        commandService.registerCommand(new UpdateOrderCommand(orderService));
        commandService.registerCommand(new DeleteOrderCommand(orderService));
        commandService.registerCommand(new GetOrderCommand(orderService));
        commandService.registerCommand(new GetAllOrderCommand(orderService));
        commandService.registerCommand(new InsertCartCommand(cartService));
        commandService.registerCommand(new UpdateCartCommand(cartService));
        commandService.registerCommand(new DeleteCartCommand(cartService));
        commandService.registerCommand(new GetCartCommand(cartService));
        commandService.registerCommand(new GetAllCartCommand(cartService));
        commandService.registerCommand(new InsertCartProductCommand(cartProductService));
        commandService.registerCommand(new UpdateCartProductCommand(cartProductService));
        commandService.registerCommand(new DeleteCartProductCommand(cartProductService));
        commandService.registerCommand(new GetCartProductCommand(cartProductService));
        commandService.registerCommand(new GetAllCartProductCommand(cartProductService));
        commandService.registerCommand(new InsertOrderProductCommand(orderProductService));
        commandService.registerCommand(new UpdateOrderProductCommand(orderProductService));
        commandService.registerCommand(new DeleteOrderProductCommand(orderProductService));
        commandService.registerCommand(new GetOrderProductCommand(orderProductService));
        commandService.registerCommand(new GetAllOrderProductCommand(orderProductService));
        commandService.registerCommand(new GetSoldProductsCommand(soldProductService));
        commandService.registerCommand(new GetReturnedOrdersCommand(returnedOrdersService));
        commandService.registerCommand(new GetCityOrdersCommand(userOrdersService));
        commandService.registerCommand(new GetHigherQuantityCommand(higherQuantityService));
        commandService.registerCommand(new GetUserOrderProductCommand(userOrderProductService));
        commandService.registerCommand(new GetAvgUserCartCommand(userCartService));
        commandService.registerCommand(new GetFirstOrderCommand(userOrdersService));
        commandService.registerCommand(new GetMostOrdersInMothCommand(userOrdersService));
        commandService.registerCommand(new GetComplitingOrdersCommand(higherQuantityService));
        commandService.registerCommand(new GetProductsAboveAveragePriceCommand(higherQuantityService));

        Scanner scanner = new Scanner(System.in);
        while (IS_ON) {

            System.out.println("Podaj komende:");
            String commandName = scanner.nextLine();
            Optional<Command> optionalCommand = commandService.findCommand(commandName);

            if (optionalCommand.isPresent()) {
                optionalCommand.get().execute();
            }
            else {
                System.out.println("Nieznana komenda");
            }

        }
    }

}
