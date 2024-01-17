package com.example.appliorganisee.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker



@Composable
fun Accueil( mapView: MapView){

    val context = LocalContext.current
    val places = mutableListOf<Place>(

    )

    val markersIdOnMap = remember{ mutableStateListOf<String>() }
    val markersWithInfoWindow = remember { mutableStateListOf<Marker>() }

    Surface(
        modifier= Modifier.fillMaxSize()
    ) {

        Box(
            modifier= Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){


            MapView(
                places = places, mapView,
                markersWithWindowOnMap = markersWithInfoWindow,
                onAddNewMarker = {
                    markersIdOnMap.add(it)
                }
            )

            PlacesListItem(places = places, onItemClickListener = { itemPlace ->
                mapView.onAnimateToNewPlace(
                    itemPlace,
                    markersWithInfoWindow
                )
            })

        }

    }



}

private fun MapView.onAnimateToNewPlace(
    itemPlace:Place,
    markswithWindowOnMap: SnapshotStateList<Marker>,
){

    this removeAllMarksFrom markswithWindowOnMap

    controller.animateTo(itemPlace.coordinates, itemPlace.focusZoomLvl, 2000L)
    Log.d("FinalMapLearnLogs", "Going to: ${itemPlace.id}")

    (overlayManager.find {
        (it is Marker && (it as Marker).id == itemPlace.id)
    } ).let{
        if(it != null) {
            (it as Marker).apply {
                showInfoWindow()
                markswithWindowOnMap.add(this)
            }
            Log.d("FinalLearning" ,"showing window on: ${it.id}")
        }else{
            Log.d("FinalLearning" ,"Null Marker for Window")
        }

    }

}

infix fun MapView.removeAllMarksFrom(markerWithWindowLis: SnapshotStateList<Marker>){
    markerWithWindowLis.apply{
        forEach {
            it.closeInfoWindow()
        }
        clear()
    }
}

@Composable
private fun MapView(places:List<Place>, mapView: MapView, onAddNewMarker:(String)->Unit, markersWithWindowOnMap: SnapshotStateList<Marker>){

    val context = LocalContext.current

    Box(
        modifier= Modifier.fillMaxSize()
    ){
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize()
        ){
            it.apply{
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                Log.d("ANDROID_MAPVIEW","Re-rendered")
            }

        }
    }

    LaunchedEffect(key1 = Unit, block = {

        val mapEventsOverlay = MapEventsOverlay(
            object: MapEventsReceiver {
                override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
                    if(markersWithWindowOnMap.isNotEmpty()){
                        mapView removeAllMarksFrom markersWithWindowOnMap
                    }
                    return true
                }

                override fun longPressHelper(p: GeoPoint?): Boolean {
                    return false
                }
            }
        )

        mapView.apply {
            controller.zoomTo(18, 1000L)

            overlays.add(mapEventsOverlay)


            for (i in places.indices) {
                onAddNewMarker(places[i].id)


            }
        }
    })

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlacesDetailItem(place:Place, onPlaceClick:()->Unit){

    Card(
        modifier= Modifier
            .height(250.dp)
            .width(200.dp)
            .padding(8.dp)
        ,
        shape = RoundedCornerShape(12.dp),
        onClick = {onPlaceClick}
    ) {

        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(8.dp)
        ){

            Image(
                painter = painterResource(id = place.imageResId), contentDescription = place.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(4.dp))

            androidx.compose.material3.Text(
                text = place.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = Color.Black,
                modifier= Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))

            androidx.compose.material3.Text(
                text = place.description,
                fontSize = 14.sp,
                color = Color.Black,
                modifier= Modifier.fillMaxWidth()
            )

        }

    }

}

@Composable
private fun PlacesListItem(places:List<Place>, onItemClickListener:(Place)->Unit){

    Box{
        Box(
            modifier= Modifier
                .fillMaxWidth()
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0x00FFFFFF), Color(0xC8000000)),
                        startY = 0f,
                        endY = 400f
                    )
                )
        )
        LazyRow(
            contentPadding = PaddingValues(8.dp),
            content = {
                items(places){place->
                    PlacesDetailItem(place = place) {
                        onItemClickListener(place)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        )
    }

}

@Composable
private fun MapSearchBar(){

    Box(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .padding(8.dp)
            .clip(RoundedCornerShape(24.dp))
            .height(24.dp)
    ){
        androidx.compose.material3.Text(text = "Search", color = Color(0xFFACACAC))
    }

}
data class Place (
    val id:String,
    val name:String,
    val coordinates: GeoPoint,
    val description:String,
    val imageResId: Int,
    val focusZoomLvl:Double
)