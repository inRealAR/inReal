package com.proct.activities.inreal.views.main.ar

import android.app.Activity
import android.app.ActivityManager
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.proct.activities.inreal.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ARActivity : AppCompatActivity() {
    var rawFoodOrDrink = 0
    private var arFragment: ArFragment? = null
    private var renderableFoodOrDrink: ModelRenderable? = null

    @RequiresApi(api = VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return
        }
        setContentView(R.layout.activity_a_r)
        val intent = intent

        rawFoodOrDrink = intent.getIntExtra("rawForObject", 0)

        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment?

        ModelRenderable.builder()
            .setSource(this, rawFoodOrDrink)
            .build()
            .thenAccept { renderable: ModelRenderable? ->
                renderableFoodOrDrink = renderable
            }
            .exceptionally { throwable: Throwable? ->
                val toast =
                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                null
            }
        arFragment!!.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane?, motionEvent: MotionEvent? ->
            if (renderableFoodOrDrink == null) {
                return@setOnTapArPlaneListener
            }

            val anchor = hitResult.createAnchor()
            val anchorNode =
                AnchorNode(anchor)
            anchorNode.setParent(arFragment!!.arSceneView.scene)

            val andy =
                TransformableNode(arFragment!!.transformationSystem)
            andy.setParent(anchorNode)
            andy.renderable = renderableFoodOrDrink
            andy.select()
        }
    }

    companion object {
        private val TAG = ARActivity::class.java.simpleName
        private const val MIN_OPENGL_VERSION = 3.0
        fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
            if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
                Log.e(TAG, "Sceneform requires Android N or later")
                Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG)
                    .show()
                activity.finish()
                return false
            }
            val openGlVersionString =
                (activity.getSystemService(ACTIVITY_SERVICE) as ActivityManager)
                    .deviceConfigurationInfo
                    .glEsVersion
            if (openGlVersionString.toDouble() < MIN_OPENGL_VERSION) {
                Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later")
                Toast.makeText(
                    activity,
                    "Sceneform requires OpenGL ES 3.0 or later",
                    Toast.LENGTH_LONG
                )
                    .show()
                activity.finish()
                return false
            }
            return true
        }
    }
}
