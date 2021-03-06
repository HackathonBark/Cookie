package hackathonbark.com.cookie;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CallbackManager callbackManager;

    private void animateAlph(int i) {
        Animation animAlpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        animAlpha.reset();
        findViewById(i).startAnimation(animAlpha);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        com.wang.avi.AVLoadingIndicatorView avi = (com.wang.avi.AVLoadingIndicatorView)findViewById(R.id.avi);
//        avi.show();

        EditText EditText_id = (EditText)findViewById(R.id.editText_id);
        EditText EditText_pw = (EditText)findViewById(R.id.editText_pw);

        Typeface type  = Typeface.createFromAsset(getAssets(), "DXPnMStd-Regular.otf");
        EditText_id.setTypeface(type);
        EditText_pw.setTypeface(type);


 //       EditText_id.setText(str);





//         Facebook Login
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "반갑습니다", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), TodayActivity.class));
                Log.d("eunchan", "success");

            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "취소되었습니다", Toast.LENGTH_LONG).show();
                Log.d("eunchan", "cancel");
            }

            @Override
            public void onError(FacebookException error) {
//                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                Log.d("eunchan", "error");
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.btnSignUp).setOnClickListener(this);
        findViewById(R.id.btnFacebook).setOnClickListener(this);

        animateAlph(R.id.imageView);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.btnLogin:
                startActivity(new Intent(this, TodayActivity.class));
                break;

            case R.id.btnSignUp:
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case R.id.btnFacebook:
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
