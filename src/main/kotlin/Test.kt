private fun main(){
    var str = "пока и пока еще раз пока"
    str = str.replace("пока", "bye")
    str = str.replace("и", "and")
    str = str.replace("еще", "another")
    str = str.replace("раз", "chance")
    println(str)
}