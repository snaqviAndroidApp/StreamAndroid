package implement.dfw.teleprompt.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import implement.dfw.teleprompt.R;
import implement.dfw.teleprompt.TestData.TestDataContent;
import implement.dfw.teleprompt.core.View;
import implement.dfw.teleprompt.ui.Teleprompter;

import static implement.dfw.teleprompt.model.Constants.REQUEST_CODE_ASK_PERMISSIONS;

public class TeleprompterEntry extends AppCompatActivity implements
        View<TestDataContent>
{

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_deploy_frags);
            CheckUserPermissions();                                                 // Check PERMISSIONS for SDK VER >= 23

            Toolbar toolBbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolBbar);
            mDrawerLayout = findViewById(R.id.drawer_rootlayout);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_pink_24dp);

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            menuItem.setChecked(true);                              // set item as selected to persist highlight
                            mDrawerLayout.closeDrawers();                           // close drawer when item is tapped
                            switch (menuItem.getItemId()) {                                         // Switch-Case for some reason doesn't work
                                case R.id.m_videoCam:
                                    startActivity(new Intent(getApplicationContext(), Teleprompter.class));
                                    break;

                                 default:
                                    return true;
                            }

                            return true;
                        }
                    });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdown_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.mdrop_sensorInteract:
                break;
            case R.id.mOptionStream:
                startActivity(new Intent(getApplicationContext(), Teleprompter.class));

            default:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void CheckUserPermissions() {                 // Permissions
        if (Build.VERSION.SDK_INT >= 23) {
            if ((checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) &&
                            (checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) &&
                            (checkCallingOrSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
            )
            {
                requestPermissions(new String[]
                                {
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.RECORD_AUDIO
                                },
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }
    }
    //----------ViewModel Section-------------------------//
    //----------------------------------------------------//

    @Override
    public void render(TestDataContent data) {          // communicate data b/w View - ViewModel

    }
}
