package com.example.project1

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bounceAnimation = AnimationUtils.loadAnimation(applicationContext,R.anim.animation)
        var numberOfTickets : Int = 1

        val Theaters = resources.getStringArray(R.array.Theaters)

        val numberOfTicketsNumberPicker = findViewById<NumberPicker>(R.id.numberOfTickets)

        numberOfTicketsNumberPicker.minValue = 1
        numberOfTicketsNumberPicker.maxValue = 10
        numberOfTicketsNumberPicker.wrapSelectorWheel = true
        numberOfTicketsNumberPicker.visibility = View.INVISIBLE

        val Datetextview = findViewById<TextView>(R.id.textView3)
        val Theatertextview = findViewById<TextView>(R.id.textView2)
        val showhidebtn = findViewById<Button>(R.id.showhidebtn)
        val buynow = findViewById<Button>(R.id.buynow)
        val Theaterspinner = findViewById<Spinner>(R.id.TheaterSpinner)
        val Datespinner =findViewById<Spinner>(R.id.DateSpinner)
        val ChooseNumberOfTicketstextview = findViewById<TextView>(R.id.textView4)
        ChooseNumberOfTicketstextview.visibility = View.INVISIBLE
        val SumTextView = findViewById<TextView>(R.id.textView5)

        SumTextView.visibility = View.INVISIBLE
        numberOfTicketsNumberPicker.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            numberOfTickets = newVal
            SumTextView.text = "Sum: " + (newVal * 10).toString() + " $"
        })
        showhidebtn.setOnClickListener{
            Datetextview.visibility = View.VISIBLE
            Theatertextview.visibility = View.VISIBLE
            Theaterspinner.visibility = View.VISIBLE
            Datespinner.visibility = View.VISIBLE
            buynow.visibility = View.VISIBLE
            ChooseNumberOfTicketstextview.visibility = View.VISIBLE
            numberOfTicketsNumberPicker.visibility = View.VISIBLE
            SumTextView.visibility = View.VISIBLE
            SumTextView.text = getString(R.string.sum) + (numberOfTickets * 10).toString()+ "$"
        }

        buynow.setOnClickListener{

            val toast = Toast.makeText(this, "Thank you for buying tickets", Toast.LENGTH_SHORT)
            toast.show()

            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView: View = inflater.inflate(R.layout.purchase_confirmation, null)

            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true // lets taps outside the popup also dismiss it

            val popupWindow = PopupWindow(popupView, width, height, focusable)


            popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)

            val purchaseTextView = popupWindow.contentView.findViewById<TextView>(R.id.Purchase)
            numberOfTickets = numberOfTicketsNumberPicker.value
            purchaseTextView.text = getString(R.string.purchase) + "\n"  + getString(R.string.purchase2) + " " + (numberOfTickets).toString() + " " + getString(R.string.purchase3)+ " " + (Theaterspinner.selectedItem).toString()  + " " + getString(R.string.purchase4)+ " " + (Datespinner.selectedItem).toString() +  " " +  " " + getString(R.string.purchase5) + " " + (numberOfTickets * 10).toString() + "$ " +  getString(R.string.purchase6)
            // dismiss the popup window when touched
            popupView.setOnTouchListener { v, event ->
                popupWindow.dismiss()
                true
            }
        }



        var PhotoVar = findViewById<ImageView>(R.id.imageView1)
        PhotoVar.setOnClickListener{
            YoYo.with(Techniques.Tada).duration(700).repeat(1).playOn(PhotoVar)
        }

        val NextWeekDates = getCurrentWeekDate(1)

        showhidebtn.startAnimation(bounceAnimation)


        if (Theaterspinner != null) {
            val Theateradapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, Theaters
            )
            Theaterspinner.adapter = Theateradapter
        }
        if (NextWeekDates != null){
            val Dateadapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,NextWeekDates)
            Datespinner.adapter = Dateadapter
        }
        Theaterspinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }




    }




    fun getCurrentWeekDate(week: Int): MutableList<String?> {

        val c = Calendar.getInstance()
        c[Calendar.SUNDAY] = Calendar.DAY_OF_MONTH

        var CalArr:MutableList<String?> = ArrayList()

        val df: DateFormat = SimpleDateFormat("dd/MM")
        for (i in 0..6) {
            CalArr.add(index=i,df.format(c.time))
            println(df.format(c.time))
            c.add(Calendar.DATE, 1)
        }
        return CalArr
    }


    }
