package com.example.appliorganisee.Utlis


import android.util.Log
import org.w3c.dom.Text

class Position(latitude : Double, longitude : Double){
    var latitude = latitude*(Math.PI/180)
        get() = field ;

        set(value) {
            field = value*(Math.PI/180)
        }
    var longitude = longitude*(Math.PI/180)
        get() = field ;

        set(value) {
            field = value*(Math.PI/180)
        }
    fun getDistance(position : Position):Double{
        val lattt = Math.cos(position.longitude-longitude)
        val intermediaire = Math.sin(latitude)*Math.sin(position.latitude)+Math.cos(latitude)*Math.cos(position.latitude)*lattt
        val Sab = 6378307 * Math.acos(intermediaire)
        return Sab
    }


    fun getAngle(position: Position):Double{

        var thirdSpot = Position(latitude*(180/Math.PI), position.longitude*(180/Math.PI))
        val hypo = this.getDistance(position)
        val oppose = position.getDistance(thirdSpot)
        Log.e("oppose", oppose.toString())
        val adjacant = this.getDistance(thirdSpot)
        Log.e("adjacant", adjacant.toString())
        val division = adjacant/hypo
        val result = Math.acos(division)*(180/Math.PI)
        return if(latitude>position.latitude){

            if (longitude>position.longitude){
                -(90+result)
            } else 90+result
        } else {
            if (longitude > position.longitude) {
                result - 90
            } else -result +90
        }

    }



    override fun toString():String{
        return ("x = ${latitude}, y = ${longitude}")
    }
}