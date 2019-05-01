package by.bsuir.proslau.goparty.ui.authorization

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import by.bsuir.proslau.goparty.App
import by.bsuir.proslau.goparty.MainActivity
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.db.local.LocalStore
import by.bsuir.proslau.goparty.db.local.UserRepository
import by.bsuir.proslau.goparty.entity.local.UserLocal
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity : AppCompatActivity() {
    private val localStore = LocalStore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        val newUser = intent.getSerializableExtra("newUser") as UserLocal

        btn_password_create.setOnClickListener {
            if(checkConfirmation()){
                newUser.password = input_password.text.toString()
                UserRepository(App.context).create(newUser)
                localStore.saveUserId(newUser.id)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else{
                Toast.makeText(App.context, "Passwords entered are different", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun checkConfirmation(): Boolean{
        if(input_password.text.toString().equals(input_confirm_password.text.toString())) return true
        return false
    }
}
