package com.example.freshlauctionapp.model

/* Fruits enum class
   - 과일 상수명(과일명, 중분류코드)
 */
enum class Fruits(val holder: String, val scode: String) {
    APPLE("사과", "060103"),
    GRAPE("포도", "060315"),
    WATERMELON("수박", "080101"),
    KOREANMELON("참외", "080201"),
    TOMATO("토마토", "080301"),
    STRAWBERRY("딸기", "080401"),
    LEEK("부추", "101001"),
    CHILI("고추", "120501"),
    CUCUMBER("오이", "090101"),
    PUMPKIN("호박", "090201"),
    LETTUCE("상추", "100501")
}
