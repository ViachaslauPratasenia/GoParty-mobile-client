package by.bsuir.proslau.goparty.ui.authorization

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import by.bsuir.proslau.goparty.MainActivity
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.logic.authorization.AuthManagerLogic
import kotlinx.android.synthetic.main.activity_auth.*
import android.widget.Toast
import by.bsuir.proslau.goparty.db.local.LocalStore
import by.bsuir.proslau.goparty.db.local.UserRepository
import by.bsuir.proslau.goparty.logic.authorization.RunnableWithError

class AuthActivity : AppCompatActivity() {
    private val authManager = AuthManagerLogic()
    private val locatStore = LocalStore()

    private val onSuccess = Runnable {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private val onFailure = object : RunnableWithError() {
        override fun run() {
            Toast.makeText(baseContext, "" + this.error.description, Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        btn_login.setOnClickListener {
            //attemptLogin()
            if(UserRepository(context = applicationContext).isUserValid(inputLoginNickname.text.toString(),
                    inputLoginPassword.text.toString())){
                val user = UserRepository(this).findUser(inputLoginNickname.text.toString())
                locatStore.saveUser(user)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
        login_tv_create_account.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun attemptLogin() {
        if (inputLoginNickname.text.toString().isEmpty()) {
            inputLoginNickname.error = resources.getString(R.string.empty_field)
            return
        }

        if (inputLoginPassword.text.toString().isEmpty()) {
            inputLoginPassword.error = resources.getString(R.string.empty_field)
            return
        }
        authManager.tryLoginWith(
            inputLoginNickname.text.toString(),
            inputLoginPassword.text.toString(),
            onSuccess,
            onFailure
        )
    }
}
