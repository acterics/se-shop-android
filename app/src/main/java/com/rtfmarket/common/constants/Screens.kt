package com.rtfmarket.common.constants

enum class Screens(val screenName: String, val position: Int = -1) {
    MAIN("main"),
    BOTTOM_NAVIGATION("bottom_navigation"),
    CATALOG("catalog", 0),

    CART("cart", 1),
    PROFILE("profile", 2),
    CATEGORY("category"),
    ORDERING("ordering")

}