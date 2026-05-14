fun main() {
    val coins: (Int) -> String = {
    //it ist der unbenannte Übergabeparamter
        "$it quarterts"       
    }
    
    val cupcake: (Int) -> String = {
    	"have a cupcake"
    }
    
    //{ "$it quarterts"} ist direkt übergebener lambdaausdruck
    //man könnte auch coins übergeben, im moment könnte man es auch löschen 
    //shorthand trailing: val treatFunction = trickOrTreat(false) { "$it quarterts"}
    val treatFunction = trickOrTreat(false, { "$it quarterts"} )
    
    
    //coins/cupcake kann auch null sein s. fun TrickOrTreat
    val trickFunction = trickOrTreat(true, cupcake)
    
    
    //so aufrufen klappt nicht, weil funktionen trick/treat nur zurückgegeben aber nicht ausgeführt werden
    //trickOrTreat(true)
    
    treatFunction()
    trickFunction()
    
    repeat(4) {treatFunction()}
}

//Lambda expression to store function directly into variable
val trick = {
    println("No treats")
}

//type specified: keine Übergabeparameter, rückgabe void = Unit
val treat: () -> Unit = {
    println("Have a treat")
}

//higher order function
//((Int) -> String)?) nullable (X)?
fun trickOrTreat(isTrick: Boolean, extraTreat:((Int) -> String)?): () -> Unit {
    if (isTrick) {
        return trick
    } else {
        if (extraTreat != null) {
            println(extraTreat(10))
        }
        
        return treat
    }
    	
}
