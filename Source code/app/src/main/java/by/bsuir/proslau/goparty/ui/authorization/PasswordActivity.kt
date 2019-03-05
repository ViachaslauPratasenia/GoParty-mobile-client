package by.bsuir.proslau.goparty.ui.authorization

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import by.bsuir.proslau.goparty.MainActivity
import by.bsuir.proslau.goparty.R
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        btn_password_create.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
