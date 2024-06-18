abstract class RestaurantActions(val resName:String, val location:String) {
    abstract val orderList: MutableList<MenuItem>
    abstract fun displayMenu()
    abstract fun processPayment()
    abstract fun takeOrder(index: Int)
    abstract fun deleteOrder(index: Int)
    abstract fun seeOrder()
}
