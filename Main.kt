fun main() {
    var run: Boolean = true

    while (run) {
        println(
            """
        Welcome to Resturant 
        1 --- > User Login
        2 --- > Admin Login
        3 --- > New User
        4 --- > Guest
        0 --- > Exit
    """.trimIndent()
        )

        val choice = try {
            readlnOrNull()?.toIntOrNull()
        } catch (e: Exception){
            println("Invalid input: ${e.message}")
            null
        }
        when (choice) {
            1 -> userLogin()
            2 -> adminLogin()
            3 -> newUser()
            0 -> {
                println("Good bye")
                run = false
            }

            else -> println("please write these(1,2,3,0)")
        }
    }
}

private fun userLogin() {
    println("Enter username")
    val username = readlnOrNull().toString();
    println("Enter password")
    val password = readlnOrNull().toString();
    try {
        if (Auth.login(username, password)) {
            println("Welcome to our restaurant")
            var run: Boolean = true
            val restaurant: RestaurantActions = Restaurant("Hesenin Tendiri", "Ismayilli")
            while (run) {
                println(
                    """
                1 - See Menu
                2 - Take Order
                3 - Delete Order Item
                4 - Process Payment
                5 - See Order
                0 - exit
            """.trimIndent()
                )
                val choice = readlnOrNull()?.toIntOrNull()
                when (choice) {
                    1 -> restaurant.displayMenu()
                    2 -> {
                        println("---Select Item ID---")
                        restaurant.displayMenu()
                        val orderChoice = readlnOrNull()?.toIntOrNull()

                        if (orderChoice != null && orderChoice <= 2 && orderChoice >= 0) {

                            restaurant.takeOrder(orderChoice)
                        } else {
                            println("!!!Invalid Item!!!")
                        }
                    }

                    3 -> {
                        println("---Select Item ID---")
                        restaurant.seeOrder()
                        val orderChoice = readlnOrNull()?.toIntOrNull()
                        if (orderChoice != null) {
                            restaurant.deleteOrder(orderChoice)
                        } else {
                            println("!!!Invalid Item!!!")
                        }
                    }

                    4 -> restaurant.processPayment()
                    5 -> restaurant.seeOrder()
                    0 -> {
                        run = false
                        println("Exit")
                    }

                    else -> println("Error")
                }
            }
        }
    } catch (e: CustomAuthException) {
        println(e.message)
    }
}


private fun adminLogin() {
    println("Enter username")
    val username = readlnOrNull().toString();
    println("Enter password")
    val password = readlnOrNull().toString();

    val admin = Admin()
    if (username == admin.getUsername() && password == admin.getPassword()) {
        println(
            """
            ********************
            You get Admin access
            ********************
        """.trimIndent()
        )
        var run: Boolean = true;

        while (run) {
            println(
                """
            ********************
            1 - See Users
            0 - exit
            ********************
        """.trimIndent()
            )
            val choice = readlnOrNull()?.toIntOrNull()
            when (choice) {
                1 -> {
                    println(Auth.getUsernames())
                }

                0 -> {
                    run = false
                }
            }
        }
    } else {
        println("Wrong Password")
    }

}

private fun newUser() {
    println("Enter username")
    val username = readlnOrNull().toString();
    println("Enter password")
    val password = readlnOrNull().toString();
    if (username.isEmpty() || password.isEmpty()) println("Username and Password require")
    else {
        val user = User(username, password)

        Auth.addUser(user)
        println(
            """
        -----------------------
        User added successfully
        Welcome to our restaurant
        -----------------------
    """.trimIndent()
        )
        userLogin()
    }

}
