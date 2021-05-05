package com.proct.activities.inreal.views.main.ar

import android.app.Activity
import android.app.ActivityManager
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.proct.activities.inreal.R
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.di.ViewModelFactory
import com.proct.activities.inreal.viewmodels.ar.ARViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@RequiresApi(VERSION_CODES.N)
class ARFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var rawFoodOrDrink = 0
    private var arFragment: ArFragment? = null
    private var renderableFoodOrDrink: ModelRenderable? = null

    private lateinit var mainNavController: NavController

    private lateinit var viewModel: ARViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainNavController = findNavController()

        viewModel = viewModels<ARViewModel> { viewModelFactory }.value

        if (!checkIsSupportedDeviceOrFinish(requireActivity())) {
            mainNavController.navigateUp()
        }
        initObservers()

        return inflater.inflate(R.layout.fragment_main__a_r, container, false)
    }



    private fun initObservers() {
        viewModel.dish.observe(viewLifecycleOwner) {
            showDishInAR(it)
        }
    }


    private fun showDishInAR(it: Dish) {

        rawFoodOrDrink = it.rawForObject
        arFragment = ArFragment()

        ModelRenderable.builder()
            .setSource(requireActivity(), rawFoodOrDrink)
            .build()
            .thenAccept { renderable: ModelRenderable? ->
                renderableFoodOrDrink = renderable
            }
            .exceptionally { throwable: Throwable? ->
                val toast =
                    Toast.makeText(
                        requireContext(),
                        "Unable to load andy renderable",
                        Toast.LENGTH_LONG
                    )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                null
            }
        Log.e("ARARARAARARARARAR", "$arFragment")
        arFragment!!.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane?, motionEvent: MotionEvent? ->
            if (renderableFoodOrDrink == null) {
                return@setOnTapArPlaneListener
            }

            // Create the Anchor.
            val anchor = hitResult.createAnchor()
            val anchorNode =
                AnchorNode(anchor)
            anchorNode.setParent(arFragment!!.arSceneView.scene)

            // Create the transformable andy and add it to the anchor.
            val andy =
                TransformableNode(arFragment!!.transformationSystem)
            andy.setParent(anchorNode)
            andy.renderable = renderableFoodOrDrink
            andy.select()
        }


//        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
//        transaction.replace(R.id.child_fragment_container, arFragment!!).commit()
    }

    companion object {
        private val TAG = ARFragment::class.java.simpleName
        private const val MIN_OPENGL_VERSION = 3.0

        /**
         * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
         * on this device.
         *
         *
         * Sceneform requires Android N on the device as well as OpenGL 3.0 capabilities.
         *
         *
         * Finishes the activity if Sceneform can not run
         */
        fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
            if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
                Log.e(TAG, "Sceneform requires Android N or later")
                Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG)
                    .show()
                return false
            }
            val openGlVersionString =
                (activity.getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager)
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
                return false
            }
            return true
        }
    }

}
