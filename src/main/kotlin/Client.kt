import alexey.tools.common.misc.ConvertUtils
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketException
import java.nio.ByteBuffer


private fun main() {
    try {
        val clientSocket = DatagramSocket()
        val IPAddress = InetAddress.getByName("95.156.230.56")

        val output = ByteArrayOutputStream()
        val string = "ÿÿÿÿTSource Engine Query"
        val long = -1

        //пример ->  "FF FF FF FF 54 53 6F 75 72 63 65 20 45 6E 67 69 6E 65 20 51 75 65 72 79 00 0A 08 5E EA"
        val result = "FF FF FF FF 54 53 6F 75 72 63 65 20 45 6E 67 69 6E 65 20 51 75 65 72 79 00 FF FF FF FF"

        getHex(string, long) //Для получения @Hex

        result.split(' ').forEach {
            output.write(it.toInt(16))
        }

        val buffer = ByteBuffer.allocate(output.size())
        buffer.put(output.toByteArray())
        val bytes = buffer.array()

        val receivingDataBuffer = ByteArray(9)
        val sendingPacket =  DatagramPacket(bytes, bytes.size, IPAddress, 27055)
        clientSocket.send(sendingPacket)

        val receivingPacket = DatagramPacket(receivingDataBuffer,receivingDataBuffer.size)
        clientSocket.receive(receivingPacket)

        println("----------------Result-----------------")
        println(ConvertUtils.toHex(receivingPacket.data).uppercase())
        println(receivingPacket.data)
        println(receivingPacket.data.contentToString())

        clientSocket.close()
    }
    catch(e: SocketException) {
        e.printStackTrace()
    }
}
private fun getHex(string: String, long: Int){
    val output = ByteArrayOutputStream()
    val writer = DataOutputStream(output)

    output.writer(Charsets.ISO_8859_1).apply {
        write(string)
        flush()
    }
    output.write(0)
    writer.writeInt(long)
    writer.flush()
    println("-----------------Hex------------------")
    println(ConvertUtils.toHex(output.toByteArray()).uppercase())
}