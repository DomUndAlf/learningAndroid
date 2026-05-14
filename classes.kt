import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String ) {
    
    //primary constructor in brackets after classname
    
    var deviceStatus = "on"
    open val deviceType = "unknown"
    
    //secondary constructor
    constructor (name: String, category: String, state: Int) : this(name, category)
    {
    statuscode = when (state) {
    0 -> "offline"
    1 -> "online"
    else -> "unknown"
    }
    }
    
    var statuscode = "unknown"
	protected set
  
    open fun turnOn() {
        statuscode = "on"
    }
    open fun turnOff() {
        statuscode = "off"
    }
    
    fun printDeviceInfo(){
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

internal class SmartTv (deviceName: String, deviceCategory: String) : 
SmartDevice(name = deviceName, category = deviceCategory){
 override val deviceType = "Smart TV"   
    
 private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)   
 private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)
   
    fun increaseVolume() {
        speakerVolume++
        println("volume increased to $speakerVolume")
    }
    
    fun decreaseVolume() {
        speakerVolume--
        println("volume decreasedto $speakerVolume")
    }
    
    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber.")
    }
    
    fun prevChannel() {
        channelNumber--
        println("Channel number decreased to &channelNumber")
    }

 override fun turnOn() {   
    super.turnOn() 
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                "set to $channelNumber."
        )
    }
 
 override fun turnOff() {
    super.turnOff()
        println("$name turned off")
    }
}

internal class SmartLight(deviceName: String, deviceCategory: String) :
	SmartDevice(name = deviceName, category = deviceCategory) {
        override val deviceType = "Smart Light"
        
 	private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }
    
    fun decreaseBrightness() {
        brightnessLevel--
        println("Brightness decreased to &brightnessLevel.")
    }
    override fun turnOn() {
      	super.turnOn()   
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")
    }
    override fun turnOff() {
        super.turnOff() 
        brightnessLevel = 0
        println("Smartlight turned off")
    }
}

internal class SmartHome (
    val smartLight: SmartLight,
    val smartTv: SmartTv
)  {
    
    var deviceTurnOnCount = 0
    private set
    
    fun turnOnTv() {
        if (smartLight.deviceStatus == "on")
        deviceTurnOnCount++
        smartLight.turnOn()
    }
    
    fun turnOffTv() {
        deviceTurnOnCount--
    	smartLight.turnOff()
	}
    
    fun increaseTvVolume() {
        smartTv.increaseVolume()
    }
    
    fun decreaseTvVolume() {
        smartTv.decreaseVolume()
    }
    
    fun changeTvChannelToNext() {
        smartTv.nextChannel()
    }
    
    fun changeTvChannelToPrevious() {
        smartTv.prevChannel()
    }
    
    fun turnOnLight() {
        deviceTurnOnCount++
        smartLight.turnOn()
    }
    
    fun turnOffLight() {
        deviceTurnOnCount--
        smartLight.turnOff()
    }
    
    fun increaseLightBrightness() {
        smartLight.increaseBrightness()
    }
    
    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
    
    fun printInfo() {
 		smartLight.printDeviceInfo()
        smartTv.printDeviceInfo()
    }
}

class RangeRegulator( 
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int) : ReadWriteProperty<Any?, Int> {
    
    var fieldData = initialValue
    
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}


fun main() {
   
    val tv = SmartTv(deviceName = "Martina", deviceCategory = "human") 
    println(tv.name)
    
    var smartDevice: SmartDevice = SmartTv("Android TV", "Entertainment")
    smartDevice.turnOn()
    
    smartDevice = SmartLight("Google Light", "Utility")
    smartDevice.turnOn()
   
    //Objekte die zum smarthome gehören vorher instantiieren
    val fernseh = SmartTv(
    deviceName = "Living Room TV",
    deviceCategory = "Entertainment"
    )

    val licht = SmartLight(
        deviceName = "Ceiling Light",
        deviceCategory = "Lighting"
    )

    val smartHome = SmartHome(
        smartLight = licht,
        smartTv = fernseh
    )
    
    smartHome.turnOnTv()
    println(smartHome.deviceTurnOnCount)
    
    smartHome.printInfo()
   
}

//private: nur in selber klasse erreichbar
//protected: in klasse, subklasse erreichbar
//internal: in klasse, subklasse, modul erreichbar
//public: überall erreichbar
