package com.churchinwales.prayer


class MainDatasource {

    fun loadPrayers() : List<Card> {
        return listOf<Card>(
            Card("Welcome to the new look front-page of the app. Here we will eventually have an updating list of prayers, messages, and updates. (<i>This section is not yet bilingual</i>)","images/Croeso.png"),
            Card("Jesus said, do this and you shall live[..]. Love the Lord Your God with all your heart, and with all your soul, and with all your strength and with all your mind and your neighbour as yourself.", "images/LoveTheLordYourGod.png"),
            Card("Who do you say that I am? You are the Messiah.", "images/Messiah1.png"),
            Card("My soul magnifies the Lord, and my Spirit rejoices in God my Saviour, for he has looked with favour on the lowliness of His servant. Surely, from now on, all generations will call me Blessed.", "images/prayer1.png"),
            Card("Arise, my Love, my fair one, and come away. For the winter is past , the rain is over and gone. The flowers appear on the earth; the time of singing has come and the voice of the turtle-dove is heard in our land ", "images/prayer2.png"),
            Card("Then from the cloud came a voice that said:'This is my Son, My Chosen; Listen to Him!", "images/Prayer3.png"),
            Card("You must understand this, my beloved: let everyone be quick to listen, slow to speak, slow to anger; for your anger does not produce God's righteousness", "images/Prayer4.png")

        )
    }
}