package by.bsuir.proslau.goparty.ui.authorization

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import by.bsuir.proslau.goparty.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_image.setOnClickListener {
            Toast.makeText(this, "Add photo", Toast.LENGTH_SHORT).show()
        }

        btn_register_next.setOnClickListener {
            val intent = Intent(this, PasswordActivity::class.java)
            startActivity(intent)
        }
    }
}
