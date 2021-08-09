package app

import java.util.*
import kotlin.math.pow
import kotlin.random.Random

class Player(var nama: String, var level: Int = 0){

    val weapon = Weapon()
    val armor = Armor()
    val id = nama.hashCode()
    var health = 100

    fun growlevel(){
        health += when(level){
            1 -> 20
            2 -> 30
            3 -> 40
            else -> 10
        }
    }

    fun dataPlayer(){
        println("""
        ----Data Player----
        | Nama   : $nama 
        | Id     : $id 
        | Level  : $level 
        | Health : $health 
        | Weapon : ${weapon.nama}
        | Armor level : ${armor.levelArmor} 
        | Attack : ${weapon.attack}
        -------------------------------------
        """.trimIndent())
    }
}

class Weapon{
    var nama: String = "-"
    var pilweapon = 0
    var attack = 0
    fun checkWeapon() {
        when(pilweapon){
            1 -> {attack += 10
                nama = "Pedang"}
            2 -> {attack += 15
                nama = "Samurai"}
            else -> {attack += 5
                nama = "Hands"}
        }
    }
}
class Armor{
    var levelArmor: Int = 0
    var plushealth: Int = 0
    fun checkArmor(): Int{
        plushealth += when(levelArmor){
            1 -> 10
            2 -> 20
            3 -> 30
            else -> 0
        }
        return plushealth
    }
}

fun main() {
    val input = Scanner(System.`in`)
    var checkmain = true
    while (checkmain){
        var check2 = true; var check3 = false; var check4 = false; var check5 = false
        var test1 = true

        menu()
        print("Masukkan jawaban anda : ")
        val siap = input.nextLine()

        if (siap == "Y"){
            println("====== PERMAINAN DIMULAI ======\n")

            var j =0 ; var check = true
            print("Masukkan nama pemain ke-${j+1} : ")
            val nama1 = input.nextLine(); j++
            print("Masukkan nama pemain ke-${j+1} : ")
            val nama2 = input.nextLine()
            val pemain1 = arrayListOf(Player(nama1),Player(nama2))
            println()

            while(check){
                menu1()
                print("Masukkan pilihan anda : ")
                when(input.nextInt()){
                    1 -> {
                        println("== DATA PEMAIN SEMENTARA ==")
                        println()
                        for (i in pemain1){
                            i.dataPlayer()
                        }
                    }
                    2 -> {
                        if (check2){
                            println("== JAWAB PERTANYAAN DIBAWAH UNTUK MENENTUKAN LEVEL PLAYER ==")
                            println()
                            var htg = 0
                            for (i in pemain1){
                                println("=Pertanyaan untuk ${i.nama}=")
                                var count = 1

                                for (k in htg..(htg+2)){
                                    println(pertanyaan()[k])
                                    print("Jawab : ")
                                    if(test1){
                                        val test = input.nextLine()
                                        test1 = false
                                    }
                                    val jwbn = input.nextLine()
                                    if (jwbn.equals(jawaban()[k])){
                                        println("Selamat! jawaban anda benar anda menjadi level $count")
                                        i.level = count
                                        count++

                                    }else{
                                        println("Jawaban anda salah, level anda tidak bertambah")
                                    }
                                    println()
                                }
                                i.growlevel()

                                println()
                                htg+=3
                            }
                            check2 = false
                            check3 = true
                        }else{
                            println("Anda telah melewati tahapan ini\n")
                        }
                    }
                    3 -> {
                        if (check3){
                            println()
                            println("== JAWAB PERTANYAAN DIBAWAH UNTUK MENENTUKAN ARMOR PLAYER ==")
                            println()
                            val randomvalue1 = List(20){Random.nextInt(1,20)}
                            val randomvalue2 = List(20){Random.nextInt(1,20)}

                            var z = 0
                            for (i in pemain1) {
                                println("=Pertanyaan untuk ${i.nama}=")
                                var y = 1
                                var count1 = 0
                                for (x in z..(z+9)) {
                                    val answer = randomvalue1[x] * randomvalue2[x]
                                    println("Pertanyaan ke-$y adalah ${randomvalue1[x]} x ${randomvalue2[x]}")
                                    print("Masukkan jawaban anda : ")
                                    val answeruser = input.nextInt()
                                    if (answeruser == answer){
                                        count1++
                                        println("Selamat! jawaban anda benar, poin anda menjadi $count1")

                                    }else{
                                        println("Jawaban anda salah, poin anda tidak bertambah")
                                    }
                                    println()
                                    y++
                                }
                                when {
                                    count1>=8 -> {
                                        i.armor.levelArmor += 3
                                    }
                                    count1>=5 -> {
                                        i.armor.levelArmor += 2
                                    }
                                    count1>=3 -> {
                                        i.armor.levelArmor += 1
                                    }
                                    else -> {
                                        i.armor.levelArmor += 0
                                    }
                                }
                                println("Selamat ${i.nama} mendapatkan poin sebanyak $count1 sehingga dapat ditukar menjadi armor level ${i.armor.levelArmor}\n")
                                i.health += i.armor.checkArmor()
                                z+=10
                                check3 = false
                                check4 = true
                            }
                        }else{
                            println("Anda telah melewati tahapan ini atau belum menyelesaikan tahapan sebelumnya\n")
                        }
                    }
                    4 -> {
                        if (check4){
                            println("== JAWAB PERTANYAAN DIBAWAH UNTUK MENENTUKAN WEAPON PLAYER ==\n")

                            val randomvalue3 = List(10){Random.nextInt(1,15)}
                            var z1 = 0
                            for (i in pemain1) {
                                println("= Pertanyaan untuk ${i.nama} =")
                                var y1 = 1
                                var count2 = 0
                                for (x1 in z1..(z1+4)) {
                                    val answer2 = randomvalue3[x1].toDouble().pow(2.0)
                                    println("Pertanyaan ke-$y1 adalah ${randomvalue3[x1]}^2 adalah")
                                    print("Masukkan jawaban anda : ")
                                    val answeruser2 = input.nextInt()
                                    if (answeruser2.toDouble() == answer2){
                                        count2++
                                        println("Selamat! jawaban anda benar, poin anda menjadi $count2\n")

                                    }else{
                                        println("Jawaban anda salah, poin anda tidak bertambah\n")
                                    }
                                    y1++
                                }
                                when {
                                    count2>=4 -> {
                                        i.weapon.pilweapon += 2
                                    }
                                    count2>=2 -> {
                                        i.weapon.pilweapon += 1
                                    }
                                    else -> {
                                        i.weapon.pilweapon += 0
                                    }
                                }
                                i.weapon.checkWeapon()
                                println()
                                println("Selamat anda mendapatkan senjata ${i.weapon.nama} dengan attack sebesar ${i.weapon.attack}")
                                println()
                                z1+=5
                            }
                            check4 = false
                            check5 = true
                        }else{
                            println("Anda telah melewati tahapan ini atau belum menyelesaikan tahapan sebelumnya\n")
                        }

                    }
                    5 -> {
                        if (check5){
                            println()
                            println("""
                            =====================
                            ||   PERTEMPURAN   ||
                            =====================
                            
                            = Pada pertempuran ini anda akan menjawab soal yang disediakan untuk menyerang lawan 
                              dengan senjata yang sudah didapatkan sebelumnya.
                            = Jika jawaban anda benar, anda akan terus dapat menjawab soal dan menyerang lawan
                            = Jika jawaban anda salah, pertanyaan anda akan dilempar kepada lawan untuk menyerang anda
                            = Jawablah seluruh soal dengan benar agar anda memenangkan pertempuran
                            = Semangat!!
                            
                            """.trimIndent())
                            val randomvalue5 = List(50){Random.nextInt(100,999)}
                            val randomvalue6 = List(50){Random.nextInt(100,999)}
                            val htgsoal = 0
                            var play = 0
                            for (soal1 in htgsoal..(htgsoal+50)){
                                println("= Pertanyaan untuk ${pemain1[play].nama} =")
                                if (play == 0){
                                    val answer3 = randomvalue5[soal1]+randomvalue6[soal1]
                                    println("Pertanyaannya adalah ${randomvalue5[soal1]} + ${randomvalue6[soal1]}")
                                    print("Masukkan jawaban anda : ")
                                    val answeruser3 = input.nextInt()
                                    if (answeruser3 == answer3){
                                        println("Selamat! jawaban anda benar, anda menyerang ${pemain1[play+1].nama} dengan attack sebesar ${pemain1[play].weapon.attack}")
                                        pemain1[play+1].health -= pemain1[play].weapon.attack
                                        println("Sisa darah dari ${pemain1[play+1].nama} adalah ${pemain1[play+1].health}\n")
                                    }else{
                                        play++
                                        println("Jawaban anda salah! Pertanyaan akan dilempar kepada lawan")
                                        print("${pemain1[play].nama}, Masukkan jawaban anda : ")
                                        val answeruser5 = input.nextInt()
                                        if (answeruser5 == answer3){
                                            println("Selamat! jawaban anda benar, anda menyerang ${pemain1[play-1].nama} dengan attack sebesar ${pemain1[play].weapon.attack}")
                                            pemain1[play-1].health -= pemain1[play].weapon.attack
                                            println("Sisa darah dari ${pemain1[play-1].nama} adalah ${pemain1[play-1].health}\n")
                                        }else{
                                            println("Jawaban anda salah! Pertanyaan akan dilanjutkan")
                                        }
                                    }
                                }else{
                                    val answer4 = randomvalue5[soal1]+randomvalue6[soal1]
                                    println("Pertanyaannya adalah ${randomvalue5[soal1]} + ${randomvalue6[soal1]}")
                                    print("Masukkan jawaban anda : ")
                                    val answeruser4 = input.nextInt()
                                    if (answeruser4 == answer4){
                                        println("Selamat! jawaban anda benar, anda menyerang ${pemain1[play].nama} dengan attack sebesar ${pemain1[play].weapon.attack}")
                                        pemain1[play-1].health -= pemain1[play].weapon.attack
                                        println("Sisa darah dari ${pemain1[play-1].nama} adalah ${pemain1[play-1].health}\n")
                                    }else{
                                        play--
                                        println("Jawaban anda salah! Pertanyaan akan dilempar kepada lawan")
                                        print("${pemain1[play].nama}, Masukkan jawaban anda : ")
                                        val answeruser6 = input.nextInt()
                                        if (answeruser6 == answer4){
                                            println("Selamat! jawaban anda benar, anda menyerang ${pemain1[play+1].nama} dengan attack sebesar ${pemain1[play].weapon.attack}")
                                            pemain1[play+1].health -= pemain1[play].weapon.attack
                                            println("Sisa darah dari ${pemain1[play+1].nama} adalah ${pemain1[play+1].health}\n")
                                        }else {
                                            println("Jawaban anda salah! Pertanyaan akan dilanjutkan")
                                        }
                                    }
                                }
                                if (pemain1[0].health <=0 || pemain1[1].health <=0){
                                    check = if (pemain1[0].health <=0){
                                        println("Selamat ${pemain1[1].nama}!! Anda menjadi pemenang untuk game ini \n")
                                        false
                                    }else{
                                        println("Selamat ${pemain1[0].nama}!! Anda menjadi pemenang untuk game ini \n")
                                        false
                                    }
                                    break
                                }
                            }
                        }else{
                            println("Anda telah melewati tahapan ini atau belum menyelesaikan tahapan sebelumnya\n")
                        }
                        check5 = false
                    }
                    else -> println("Masukkan salah! Ulangi")
                }
                println("Permainan selesai!!")
            }
        }else{
            println("Anda belum siap untuk bermain game ini")
            break
        }
        println("Apakah anda ingin bermain lagi? (Yes/No)")
        print("Masukkan jawaban anda: ")
        val test3 = input.nextLine()
        val jwb1 = input.nextLine()
        when {
            jwb1.equals("Yes") -> {
                println("Selamat bermain kembali")
            }
            jwb1.equals("No") -> {
                println("Terimakasih sudah bermain!")
                checkmain = false
            }
            else -> {
                println("Masukkan salah! Program akan berhenti")
                checkmain = false
            }
        }
    }

    println("Program berakhir...")

}
fun menu(){
    println("""
      ===SELAMAT DATANG DI GAME JAWAB SOAL===
      = Pada game ini anda akan diminta untuk bertarung melawan 
        pemain lain dengan cara mengerjakan soal matematika
        sederhana maupun pengetahuan umum yang sudah disediakan
      = jika jawaban berupa kalimat atau kata jangan terdapat
        huruf kapital
        
      = Anda siap? (Y/T)
        
    """.trimIndent())
}
fun pertanyaan(): Array<String>{
    val soal = Array(6){""}
    soal[0] = "Negara di Asia yang tidak pernah dijajah adalah ?"
    soal[1] = "Negara di Asia yang tidak memiliki lautan adalah ?"
    soal[2] = "Negara kelahiran Cristiano Ronaldo adalah?"
    soal[3] = "Negara kelahiran Lionel Messi adalah ?"
    soal[4] = "Negara dengan penduduk terbanyak adalah?"
    soal[5] = "Negara dengan sistem pendidikan terbaik di Asia adalah ?"
    return soal
}
fun jawaban(): Array<String>{
    val jwb = Array(6){""}
    jwb[0] = "thailand"
    jwb[1] = "laos"
    jwb[2] = "portugal"
    jwb[3] = "argentina"
    jwb[4] = "cina"
    jwb[5] = "finlandia"
    return jwb
}
fun menu1(){
    println("""
        Pilihan menu permainan
        1. Melihat data pemain saat ini
        2. Menjawab soal untuk menentukan level pemain
        3. Menjawab soal untuk menentukan armor pemain
        4. Menjawab soal untuk menentukan senjata pemain
        5. Menjawab soal untuk mengalahkan lawan
    """.trimIndent())
}