data class User(val username: String, val password: String)

class Auth {
    companion object {
        private val users = mutableListOf<User>()

        fun addUser(user: User) {
            users.add(user)
        }

        fun getUsersCount(): Int {
            return users.size
        }

        fun getUsernames(): List<String> {
            return users.map { it.username }
        }

        @Throws(CustomAuthException::class)
        fun login(username: String, password: String): Boolean {
            val user = users.find { it.username == username && it.password == password }
            if (user != null) {
                return true
            } else {
                throw CustomAuthException("Invalid username or password")
            }
        }

        @Throws(CustomAuthException::class)
        fun adminLogin(username: String, password: String): Boolean {
            if (username == "hasan" && password == "vallahbilmirem") {
                return true
            } else {
                throw CustomAuthException("Admin authentication failed")
            }
        }
    }
}
