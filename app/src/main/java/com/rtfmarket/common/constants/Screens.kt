package com.rtfmarket.common.constants

enum class Screens(val screenName: String, val position: Int = -1) {
    MAIN("main"),
    BOTTOM_NAVIGATION("bottom_navigation"),
    CATALOG("catalog", 0),

    CART("cart", 1),
    PROFILE_HOLDER("profile", 2),
    PROFILE_DATA("profile_data"),
    PROFILE_NOT_AUTH("profile_not_auth"),

    CATEGORY("category"),
    ORDERING("ordering"),
    PRODUCT("product"),

    AUTHENTICATION("authentication"),
    SIGN_IN("sign_in"),
    SIGN_UP("sign_up")

}