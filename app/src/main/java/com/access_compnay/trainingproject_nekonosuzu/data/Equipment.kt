package com.access_compnay.trainingproject_nekonosuzu.data

class Equipment {
    lateinit var id: String
    var name: String? = null
    var os: String? = null
    var currentUserName: String? = null

    override fun toString(): String {
        return "$name $os $currentUserName"
    }


}