class ModelUser {

    var id: Int = 0
    var userName: String? = null
    var password : String? =null

    constructor(id: Int, userName: String?, password: String?) {
        this.id = id
        this.userName = userName
        this.password = password
    }

    constructor(userName: String?) {
        this.userName = userName
    }


}