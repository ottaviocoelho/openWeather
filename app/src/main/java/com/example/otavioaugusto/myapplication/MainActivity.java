package com.example.otavioaugusto.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.sql.SQLException;

import controllers.CityListController;

import static com.example.otavioaugusto.myapplication.R.id.city_list_view;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt = (Button) findViewById(R.id.button_new_forecast);
        bt.setOnClickListener(this);
//        checkPermissions();
        try {
            CityListController.getInstance().init(this, city_list_view);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newForecast() {
        Intent intent = new Intent(this, AddForecastActivity.class);
        startActivity(intent);
    }

    public void forecastDetail(Integer id) {
        Intent intent = new Intent(MainActivity.this, ForecastDetailActivity.class);
        intent.putExtra("id", id.intValue());
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CityListController.getInstance().closeHelper();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_new_forecast:
                newForecast();
//                try {
//                    backupDatabase();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                break;
        }
    }

//
//    private void backupDatabase() throws IOException {
//        File database = getDatabasePath(DatabaseHelper.DATABASE_NAME);
//        if (database.exists() && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            File outputPath = getBackupPath();
//            OutputStream myOutput = new FileOutputStream(new File(outputPath, DatabaseHelper.DATABASE_NAME));
//            InputStream myInput = new FileInputStream(getDatabasePath(DatabaseHelper.DATABASE_NAME));
//
//            writeBuffer(myOutput, myInput);
//        }
//    }
//
//    private void writeBuffer(OutputStream myOutput, InputStream myInput) throws IOException {
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = myInput.read(buffer)) > 0) {
//            myOutput.write(buffer, 0, length);
//        }
//        myOutput.flush();
//        myOutput.close();
//        myInput.close();
//    }
//
//
//    private File getBackupPath() {
//        File path = new File(Environment.getExternalStorageDirectory() + "/teste/backup/");
//        if (!path.exists()) {
//            path.mkdirs();
//        }
//        return path;
//    }
//
//    private void checkPermissions(){
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }
//    }


}
