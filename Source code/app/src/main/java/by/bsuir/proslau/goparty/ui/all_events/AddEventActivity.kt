package by.bsuir.proslau.goparty.ui.all_events

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import by.bsuir.proslau.goparty.R
import kotlinx.android.synthetic.main.activity_add_event.*

class AddEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        addEventBtnBack.setOnClickListener {
            finish()
        }

        addEventAddPhoto.setOnClickListener {
            Toast.makeText(this, "Add photo called", Toast.LENGTH_SHORT).show()
        }

        addEventFinishBtn.setOnClickListener {
            Toast.makeText(this, "Event added", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
