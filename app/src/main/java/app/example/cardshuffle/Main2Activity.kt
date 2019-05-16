package app.example.cardshuffle

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {
    val TAG = "MainActivity"

    //todo : Make the flip animation start on click event
//    t odo : Use the card in a list so that scatter works after flip : done
//    todo: use the xml animation selector.


    var cardList = ArrayList<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        cardList.add(card3)
        cardList.add(card2)
        cardList.add(card1)
        addCards()
        scatter.setOnClickListener {
            scatterCards()
        }
        collect_cards.setOnClickListener { collectCards() }
        flip_cards.setOnClickListener { flipCards() }
    }

    private fun flipCards() {
        val animation0: MutableLiveData<Boolean> = MutableLiveData()
        val animation1: MutableLiveData<Boolean> = MutableLiveData()
        val animation2: MutableLiveData<Boolean> = MutableLiveData()
        val animationEndListener: Observer<Boolean> = Observer {
            //todo replace items here
        }
        val resultantAnimation: CombinedLiveData<Boolean, Boolean, Boolean, Boolean> = CombinedLiveData(animation1, animation2, animation0) { animation1, animation2, animation0 ->
            var result = true
            result = if (animation0 != null)
                result && animation0
            else false
            result = if (animation1 != null)
                result && animation1
            else false
            result = if (animation2 != null)
                result && animation2
            else false
            result
        }
        cardList[2].animate()
                .rotation(45f)
                .translationX(resources.getDimension(R.dimen.translationX))
                .translationYBy(2 * resources.getDimension(R.dimen.translationYMax))
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setInterpolator(FastOutSlowInInterpolator())
                .setListener(object : AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                        Log.d(TAG, "animation repeating")
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        Log.d(TAG, "animation cancelled")
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        Log.d(TAG, "animation started")
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        cardList[2].animate()
                                .rotation(0f)
                                .translationX(0f)
                                .scaleX(1f)
                                .scaleY(1f)
                                .translationY(-2 * resources.getDimension(R.dimen.translationY))
                                .translationZ(5f)
                                .setInterpolator(FastOutSlowInInterpolator())
                                .setListener(object : AnimatorListener {
                                    override fun onAnimationRepeat(animation: Animator?) {
                                        Log.d(TAG, "animation repeating")
                                    }

                                    override fun onAnimationEnd(animation: Animator?) {
                                        animation2.postValue(true)
                                    }

                                    override fun onAnimationCancel(animation: Animator?) {
                                        Log.d(TAG, "animation cancelled")
                                    }

                                    override fun onAnimationStart(animation: Animator?) {
                                        Log.d(TAG, "animation started")
                                    }

                                })
                                .start()
                        cardList[0].animate()
                                .translationY(resources.getDimension(R.dimen.translationY))
                                .setInterpolator(OvershootInterpolator(3f))
                                .setListener(object : AnimatorListener {
                                    override fun onAnimationRepeat(animation: Animator?) {

                                    }

                                    override fun onAnimationEnd(animation: Animator?) {
                                        animation0.postValue(true)
                                    }

                                    override fun onAnimationCancel(animation: Animator?) {
                                    }

                                    override fun onAnimationStart(animation: Animator?) {
                                    }
                                })
                                .start()
                        cardList[1].animate().translationY(resources.getDimension(R.dimen.translationY))
                                .setInterpolator(OvershootInterpolator(3f))
                                .setListener(object : AnimatorListener {
                                    override fun onAnimationRepeat(animation: Animator?) {

                                    }

                                    override fun onAnimationEnd(animation: Animator?) {
                                        animation1.postValue(true)
                                    }

                                    override fun onAnimationCancel(animation: Animator?) {
                                    }

                                    override fun onAnimationStart(animation: Animator?) {
                                    }
                                })
                                .start()
                    }
                })
                .start()

        resultantAnimation.observeForever(animationEndListener)
    }

    private fun collectCards() {
        cardList[1].animate()
                .rotation(0f)
                .translationX(0f)
                .setInterpolator(OvershootInterpolator(3f))
                .start()
        cardList[0].animate()
                .rotation(0f)
                .translationX(0f)
                .translationY(0f)
                .setInterpolator(OvershootInterpolator(3f))
                .start()
        cardList[2].animate()
                .rotation(0f)
                .translationX(0f)
                .translationY(0f)
                .setInterpolator(OvershootInterpolator(3f))
                .start()
    }

    private fun scatterCards() {
        cardList[0].animate()
                .rotation(-30f)
                .translationX(-1 * resources.getDimension(R.dimen.translationX))
                .translationY(-1 * resources.getDimension(R.dimen.translationY))
                .setInterpolator(OvershootInterpolator(3f))
                .start()
        cardList[1].animate()
                .translationX(50f)
                .setInterpolator(OvershootInterpolator(3f))
                .start()
        cardList[2].animate()
                .rotation(30f)
                .translationX(resources.getDimension(R.dimen.translationX))
                .translationY(resources.getDimension(R.dimen.translationY))
                .setInterpolator(OvershootInterpolator(3f))
                .start()
    }

    private fun addCards() {
        card1.tag = "card1"
        card2.tag = "card2"
        card3.tag = "card3"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            (cardList[2] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.card_3, null))
        } else {
//            card3.setBackgroundColor(resources.getColor(R.color.card_3))
            (cardList[2] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.card_3))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            (cardList[1] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.card_2, null))
        } else {
            (cardList[1] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.card_2))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            (cardList[0] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.light_grey, null))
        } else {
            (cardList[0] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.light_grey))
        }


    }
}
