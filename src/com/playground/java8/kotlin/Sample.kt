package com.singledraft.peekuchat.core.services.service.locator


/**
 * Created by Talha Hasan Zia on 09-Mar-18.
 * <p></p><b>Description:</b><p></p> Why class was created?
 * <p></p>
 * <b>Public Methods:</b><p></p> Only listing to public methods usage.
 */
class Sample (address:String, model:String){
    private lateinit var id:String

    constructor(name:String):this(address =" some address" ,model = " some model")
    {
        System.out.print( "Constructor: "+name)
    }

    init {
        id=" Some Id"
        System.out.print("Initializer: "+id)
    }


    fun showInfo()
    {
       // Log.d("INIT","Address: "+address)
    }
}