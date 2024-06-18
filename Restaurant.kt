class Restaurant(resName: String, location: String) : RestaurantActions(resName, location) {
    override val orderList: MutableList<MenuItem> = mutableListOf()

    private val menu = listOf(
        MenuItem("Burger", 1.99, MenuCategory.MAIN_COURSE),
        MenuItem("Dolma", 8.00, MenuCategory.MAIN_COURSE),
        MenuItem("Sprite", 1.00, MenuCategory.DRINK),
    )

    override fun displayMenu() {
        println("Menu at $resName:")
        for ((index, item) in menu.withIndex()) {
            println("${index}. ${item.name} - $${item.price} (${item.category})")
        }
    }

    override fun takeOrder(index: Int) {
        try {
            println("Add to Order")
            orderList.add(menu[index])
            println("Item added successfully...")
        }catch (e: IndexOutOfBoundsException){
            println("Invalid menu index")
        }
    }

    override fun deleteOrder(index: Int) {
        if (orderList.isEmpty()) {
            println("Order is empty")
        } else {
            try {
                println("Delete Order Item")
                orderList.removeAt(index)
                println("Item deleted successfully...")
            } catch (e: IndexOutOfBoundsException) {
                println("Invalid item index")
            }
        }
    }

    override fun seeOrder() {
        println("Current Order:")
        if (orderList.isEmpty()) {
            println("Order is empty")
        } else {
            for ((index, menuItem) in orderList.withIndex()) {
                println("${index}. ${menuItem.name} - $${menuItem.price}")
            }
        }
    }

    override fun processPayment() {
        var totalAmount = 0.0
        for (menuItem in orderList) {
            totalAmount += menuItem.price
        }
        println("Total amount to pay: $totalAmount")
        orderList.clear()
    }
}
