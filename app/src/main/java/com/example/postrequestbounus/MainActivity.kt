package com.example.postrequestbounus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var addbtn=findViewById<Button>(R.id.button)
        var etName=findViewById<EditText>(R.id.editTextTextPersonName)
        var text=findViewById<TextView>(R.id.textView2)
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        var viewbtn=findViewById<Button>(R.id.button2)
        addbtn.setOnClickListener {
         val name=etName.text.toString()
            val pk=1
            apiInterface!!.addUsers(PeopleItem(name,pk)).enqueue(object: Callback<PeopleItem> {
                override fun onResponse(
                    call: Call<PeopleItem>,
                    response: Response<PeopleItem>
                ) {
                    Toast.makeText(this@MainActivity, "The User Has Been Added Successfully!!", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<PeopleItem>, t: Throwable) {
                    Toast.makeText(applicationContext, "Sorry,The User Has Not Been Added Successfully!!", Toast.LENGTH_SHORT).show()
                }

            }
            )
        }
        viewbtn.setOnClickListener {
            apiInterface!!.getUsers().enqueue(object:Callback<ArrayList<PeopleItem>>{

                override fun onFailure(call: Call<ArrayList<PeopleItem>>, t: Throwable) {
                    call.cancel()
                }
                override fun onResponse(
                    call: Call<ArrayList<PeopleItem>>,
                    response: Response<ArrayList<PeopleItem>>
                ) {
                    val list = response.body()!!

                        var users=""
                         var number =0
                   for(user in list){
                       number =number+1
                      val user = "${number} - ${user.name}\n"
                       users+=user
                      }
                     println(users)
                 text.text = "$users"

                }
            })


        }
    }



    }
