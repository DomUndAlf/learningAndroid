open class SmartDevice(val name: String, val category: String ) {
	var statuscode = "unknown"
    open val deviceType = "unknown"
    
    constructor (name: String, category: String, state: Int) : this(name, category)
    {
    statuscode = when (state) {
    0 -> "offline"
    1 -> "online"
    else -> "unknown"
    }
    }
        
    open fun turnOn() {
        statuscode = "on"
    }
    open fun turnOff() {
        statuscode = "off"
    }
}

class SmartTv (deviceName: String, deviceCategory: String) : 
SmartDevice(name = deviceName, category = deviceCategory){
 override val deviceType = "Smart TV"   
    
 var channelNumber = 1
    set(value) {
        if (value in 0..200) {
            field = value
        }
    } 
var speakerVolume = 2
    set(value) {
        if (value in 0..100) {
            field = value
        }
    }
    fun increaseVolume() {
        speakerVolume++
        println("volume increased to $speakerVolume")
    }
    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber.")
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

class SmartLight(deviceName: String, deviceCategory: String) :
	SmartDevice(name = deviceName, category = deviceCategory) {
        override val deviceType = "Smart Light"
        
        var brightnessLevel = 0
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
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

class SmartHome (
    val smartLight: SmartLight,
    val smartTv: SmartTv
)  {
    fun turnOnTv() {
        smartLight.turnOn()
    }
    fun turnOffTv() {
    smartLight.turnOff()
	}
    fun increaseTvVolume() {
        smartTv.increaseVolume()
    }
    fun changeTvChannelToNext() {
        smartTv.nextChannel()
    }
    fun turnOnLight() {
        smartLight.turnOn()
    }
    fun turnOffLight() {
        smartLight.turnOff()
    }
    fun increaseLightBrightness() {
        smartLight.increaseBrightness()
    }
    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

fun main() {
   
    val tv = SmartTv(deviceName = "Martina", deviceCategory = "human")
    tv.increaseVolume()
    tv.nextChannel()  
    println(tv.name)
    
    var smartDevice: SmartDevice = SmartTv("Android TV", "Entertainment")
    smartDevice.turnOn()
    
    smartDevice = SmartLight("Google Light", "Utility")
    smartDevice.turnOn()
   
}
