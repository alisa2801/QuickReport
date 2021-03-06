package com.AlisaCiubotaru.quickreport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class FloorPlanLoaded extends AppCompatActivity implements View.OnTouchListener {
    //cautat textviewul din problemReporter
//    TextView textView = (TextView) findViewById(R.id.textView3) ;
    private static String selectedroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplanloaded);

        ImageView iv = (ImageView) findViewById (R.id.image);
        if (iv != null) {
            iv.setOnTouchListener(this);
        }

        toast ("Touch the screen to discover where the regions are.");
    }


    /**
     * Respond to the user touching the screen.
     * Change images to make things appear and disappear from the screen.
     *
     */

    public boolean onTouch (View v, MotionEvent ev)
    {
        boolean handledHere = false;

        final int action = ev.getAction();

        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();
        int nextImage = -1;			// resource id of the next image to display

        // If we cannot find the imageView, return.
        ImageView imageView = (ImageView) v.findViewById (R.id.image);
        if (imageView == null) return false;

        // When the action is Down, see if we should show the "pressed" image for the default image.
        // We do this when the default image is showing. That condition is detectable by looking at the
        // tag of the view. If it is null or contains the resource number of the default image, display the pressed image.
        Integer tagNum = (Integer) imageView.getTag ();
        int currentResource = (tagNum == null) ? R.drawable.p2_ship_default : tagNum.intValue ();

        // Now that we know the current resource being displayed we can handle the DOWN and UP events.

        switch (action) {
            case MotionEvent.ACTION_DOWN :
                if (currentResource == R.drawable.p2_ship_default) {
                    nextImage = R.drawable.p2_ship_pressed;
                    handledHere = true;
       /*
       } else if (currentResource != R.drawable.p2_ship_default) {
         nextImage = R.drawable.p2_ship_default;
         handledHere = true;
       */
                } else handledHere = true;
                break;

            case MotionEvent.ACTION_UP :
                // On the UP, we do the click action.
                // The hidden image (image_areas) has three different hotspots on it.
                // The colors are red, blue, and yellow.
                // Use image_areas to determine which region the user touched.
                int touchColor = getHotspotColor (R.id.image_areas, evX, evY);

                // Compare the touchColor to the expected values. Switch to a different image, depending on what color was touched.
                // Note that we use a Color Tool object to test whether the observed color is close enough to the real color to
                // count as a match. We do this because colors on the screen do not match the map exactly because of scaling and
                // varying pixel density.
                ColorTool ct = new ColorTool ();
                int tolerance = 25;
                nextImage = R.drawable.p2_ship_default;
                if (ct.closeMatch (Color.RED, touchColor, tolerance)){
                    //nextImage = R.drawable.p2_ship_powered;
                    //se salveaza numele incaperii intr-un vector or smth
//                    textView.setText("Rosu");
//                    setContentView(R.layout.activity_problemreporter);
//                    setContentView(R.layout.activity_report);
//                    TextView textCuloare = (TextView) findViewById(R.id.reportRoom);
//                    textCuloare.setText("Great Room");
                    selectedroom ="Great Room";
                    openProblemReporter();
                }
                else if (ct.closeMatch (Color.BLUE, touchColor, tolerance)){
//                    setContentView(R.layout.activity_report);
//                    TextView textCuloare = (TextView) findViewById(R.id.reportRoom);
//                    textCuloare.setText("BedRoom");
                    selectedroom ="Bedroom";
                    openProblemReporter();
                }
                else if (ct.closeMatch (Color.YELLOW, touchColor, tolerance)) nextImage = R.drawable.p2_ship_no_star;
                else if (ct.closeMatch (Color.WHITE, touchColor, tolerance)) nextImage = R.drawable.p2_ship_default;

                // If the next image is the same as the last image, go back to the default.
                // toast ("Current image: " + currentResource + " next: " + nextImage);
                if (currentResource == nextImage) {
                    nextImage = R.drawable.p2_ship_default;
                }
                handledHere = true;
                break;

            default:
                handledHere = false;
        } // end switch

        if (handledHere) {

            if (nextImage > 0) {
                imageView.setImageResource (nextImage);
                imageView.setTag (nextImage);
            }
        }
        return handledHere;
    }

    /**
     * Resume the activity.
     */

    @Override protected void onResume() {
        super.onResume();
    }


/**
 */
// More methods

    /**
     * Get the color from the hotspot image at point x-y.
     *
     */

    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById (hotspotId);
        if (img == null) {
            Log.d ("ImageAreasActivity", "Hot spot image not found");
            return 0;
        } else {
            img.setDrawingCacheEnabled(true);
            Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
            if (hotspots == null) {
                Log.d ("ImageAreasActivity", "Hot spot bitmap was not created");
                return 0;
            } else {
                img.setDrawingCacheEnabled(false);
                return hotspots.getPixel(x, y);
            }
        }
    }

    /**
     * Show a string on the screen via Toast.
     *
     * @param msg String
     * @return void
     */

    public void toast (String msg)
    {
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_LONG).show ();
    } // end toast

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void openProblemReporter(){
        Intent intent = new Intent(this, Report.class);
//        Intent intent = new Intent(this, ProblemReporter.class);
        startActivity(intent);
    }

    public static String getRoom(){
        return selectedroom;
    }
} // end class