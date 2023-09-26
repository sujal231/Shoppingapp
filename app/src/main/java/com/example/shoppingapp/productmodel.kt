package com.example.exam

class productmodel {

    lateinit var key: String
    lateinit var title: String
    lateinit var description: String
    lateinit var img: String
    lateinit var price: String

    constructor()
    constructor (key: String, title: String, description: String, img: String, price: String) {
        this.key = key
        this.title = title
        this.description = description
        this.img = img
        this.price = price
    }
}