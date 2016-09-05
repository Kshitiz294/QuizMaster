package kshitizgupta.quizmaster;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayFragment extends Fragment {

    private View view;

    TextView questions_textView;
    QuestionItem[] questionItems;

    private static int[] random_questions;
    private static int random_questions_size;
    int randomInt;
    public PlayFragment() {
    }

    public interface MessageInterface{
        void sendMessage(String string);
    }

    public void respondToMessage(String string) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionItems= new QuestionItem[2];
        questionItems[0]= new QuestionItem();
        questionItems[0].setQuestion("When I take my clothes off it puts it on and when it takes them off I put them on. What am I?");
        questionItems[0].setOptions(new String[]{"Hanger","Washing Line","Nightwear","Pyjamas"});
        questionItems[0].setRight_option(0);
        questionItems[1]= new QuestionItem();
        questionItems[1].setQuestion("I am a word following cup, wedding and sponge. What am I?");
        questionItems[1].setOptions(new String[]{"Day","Pudding","Win","Cake"});
        questionItems[1].setRight_option(3);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.play_screen,container,false);
        random_questions= new int[]{0,1};
        random_questions_size= random_questions.length;

        setupOnClickListeners();
        questions_textView=(TextView)view.findViewById(R.id.questions_textview);
        setUpQuestion();

        return view;
    }

    public void setUpQuestion(){


        Random random= new Random();
        randomInt= random.nextInt(random_questions_size);

        for (int i=randomInt;i<random_questions_size-1;i++){
            random_questions[i]=random_questions[i+1];
        }
        random_questions_size--;
        setUpLayout(randomInt);
    }

    private void setUpLayout(int rand){
        int[] rand_options={0,1,2,3};
        int rand_options_size= rand_options.length;

        questions_textView.setText(questionItems[rand].question);


        TableLayout T = (TableLayout) view.findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x = 0; x < R.getChildCount(); x++) {
                    if (R.getChildAt(x) instanceof Button) {
                        Button B = (Button) R.getChildAt(x);
                        B.setEnabled(true);
                        Random random=new Random();
                        int next= random.nextInt(rand_options_size);
                        B.setText(questionItems[rand].options[rand_options[next]]);
                        for (int i=next;i<rand_options_size-1;i++){
                            rand_options[i]=rand_options[i+1];
                        }
                        rand_options_size--;
                    }
                }
            }
        }
    }


    /**
     * Disables all the buttons.
     */
    private void disableGameButtons() {
        TableLayout T = (TableLayout) view.findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x = 0; x < R.getChildCount(); x++) {
                    if (R.getChildAt(x) instanceof Button) {
                        Button B = (Button) R.getChildAt(x);
                        B.setEnabled(false);
                    }
                }
            }
        }
    }

    private void setupOnClickListeners() {
        TableLayout T = (TableLayout) view.findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x = 0; x < R.getChildCount(); x++) {
                    View V = R.getChildAt(x); // In our case this will be each button on the grid
                    V.setOnClickListener(new PlayButtonOnClick(x, y));
                }
            }
        }
    }

    /**
     * Handles click event on the buttons
     */
    private class PlayButtonOnClick implements View.OnClickListener {

        private int x = 0;
        private int y = 0;

        public PlayButtonOnClick(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void onClick(View view) {
            if (view instanceof Button) {
                Button B = (Button) view;
                if(B.getText().equals(questionItems[randomInt].right_option)){
                    Toast.makeText(getActivity(),"Right Answer",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"Wrong Answer",Toast.LENGTH_SHORT).show();
                }
                setUpQuestion();
            }
        }
    }


    public class QuestionItem{
        public String question;
        public String[] options;
        public int right_option;

        public QuestionItem() {
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public void setRight_option(int right_option) {
            this.right_option = right_option;
        }

        public void setOptions(String[] options) {
            this.options = options;
        }
    }

}
