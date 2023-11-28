package com.example.castresautresor

import android.util.Log

class Position(latitude : Double, longitude : Double){
    var latitude = latitude*(Math.PI/180)
    var longitude = longitude*(Math.PI/180)
    get() = field
    override fun toString():String{
        return ("x = ${latitude}, y = ${longitude}")
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
        if(latitude>position.latitude){return 180-result}
        else return result
    }
}