open class SmartDevice(val name: String, val category: String ) {
	var statuscode = "unknown"
    
    constructor (name: String, category: String, state: Int) : this(name, category)
    {
    statuscode = when (state) {
    0 -> "offline"
    1 -> "online"
    else -> "unknown"
    }
    }
        
    fun turnOn() {
        println("Smart device is turned on.")
    }
    fun turnOff() {
        println("Smart device is turned off.")
    }
}

class SmartPhone (deviceName: String, deviceCategory: String) : 
SmartDevice(name = deviceName, category = deviceCategory){
    
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
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
	SmartDevice(name = deviceName, category = deviceCategory) {
        
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
    }

class SmartHome (val smartTvDevice: SmartTvDevice)  {
}

fun main() {
    val tv = SmartDevice(name = "Fred", category = "human", state = 0)
 
    tv.turnOn()
    tv.turnOff()
    println ("devide name is ${tv.name} of category ${tv.category} and has status ${tv.statuscode}")

    val phone = SmartPhone(deviceName = "Martina", deviceCategory = "human")
    phone.increaseVolume()
    phone.nextChannel()  
    println(phone.name)
}

