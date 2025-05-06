package com.example.questionnaire_v3.ui.analytics;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.questionnaire_v3.MainActivity;
import com.example.questionnaire_v3.R;
import com.example.questionnaire_v3.ui.login.LoginViewModel;
import com.example.questionnaire_v3.ui.registration.RegistrationFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.text.Format;
import java.text.SimpleDateFormat;


public class FragmentAnalytics extends Fragment{

    private AnalyticsViewModel mViewModel;


    // TODO: Rename and change types and number of parameters
    public static com.example.questionnaire_v3.ui.login.LoginFragment newInstance() {
        return new com.example.questionnaire_v3.ui.login.LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AnalyticsViewModel.class);
     //   mViewModel.context = getActivity().getApplicationContext();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_analytics, container, false);

        GraphView graph = (GraphView) V.findViewById(R.id.graph);
        TextView tv_text_1 = (TextView) V.findViewById(R.id.legend_answer_1);
        TextView tv_text_2 = (TextView) V.findViewById(R.id.legend_answer_2);
        TextView tv_text_3 = (TextView) V.findViewById(R.id.legend_answer_3);
        TextView tv_text_4 = (TextView) V.findViewById(R.id.legend_answer_4);
        TextView tv_text_5 = (TextView) V.findViewById(R.id.legend_answer_5);

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                // return as Integer
                return ""+((int) value);
            }
        });


        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 0),
                new DataPoint(2, 0),
                new DataPoint(3, 0),
                new DataPoint(4, 0),
                new DataPoint(5, 1),
                new DataPoint(6, 0),
                new DataPoint(7, 0),
                new DataPoint(8, 0),
                new DataPoint(9, 0),
                new DataPoint(10, 0),
                new DataPoint(11, 0),
                new DataPoint(12, 0),
                new DataPoint(13, 0),
                new DataPoint(14, 0),
                new DataPoint(15, 0),
                new DataPoint(16, 0),
                new DataPoint(17, 0),
                new DataPoint(18, 0),
                new DataPoint(19, 0),
                new DataPoint(20, 0),
                new DataPoint(21, 0),
                new DataPoint(22, 0),
                new DataPoint(23, 0),
                new DataPoint(24, 0),
                new DataPoint(25, 0),
                new DataPoint(26, 0),
                new DataPoint(27, 0),
                new DataPoint(28, 0),
                new DataPoint(29, 0),
                new DataPoint(30, 0),
        });
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 0),
                new DataPoint(2, 0),
                new DataPoint(3, 0),
                new DataPoint(4, 0),
                new DataPoint(5, 0),
                new DataPoint(6, 0),
                new DataPoint(7, 0),
                new DataPoint(8, 0),
                new DataPoint(9, 0),
                new DataPoint(10, 0),
                new DataPoint(11, 0),
                new DataPoint(12, 0),
                new DataPoint(13, 0),
                new DataPoint(14, 0),
                new DataPoint(15, 0),
                new DataPoint(16, 0),
                new DataPoint(17, 0),
                new DataPoint(18, 0),
                new DataPoint(19, 0),
                new DataPoint(20, 0),
                new DataPoint(21, 0),
                new DataPoint(22, 0),
                new DataPoint(23, 0),
                new DataPoint(24, 0),
                new DataPoint(25, 0),
                new DataPoint(26, 0),
                new DataPoint(27, 0),
                new DataPoint(28, 0),
                new DataPoint(29, 0),
                new DataPoint(30, 0),
        });
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 0),
                new DataPoint(2, 0),
                new DataPoint(3, 0),
                new DataPoint(4, 0),
                new DataPoint(5, 0),
                new DataPoint(6, 0),
                new DataPoint(7, 0),
                new DataPoint(8, 0),
                new DataPoint(9, 0),
                new DataPoint(10, 0),
                new DataPoint(11, 0),
                new DataPoint(12, 0),
                new DataPoint(13, 0),
                new DataPoint(14, 0),
                new DataPoint(15, 0),
                new DataPoint(16, 0),
                new DataPoint(17, 0),
                new DataPoint(18, 0),
                new DataPoint(19, 0),
                new DataPoint(20, 0),
                new DataPoint(21, 0),
                new DataPoint(22, 0),
                new DataPoint(23, 0),
                new DataPoint(24, 0),
                new DataPoint(25, 0),
                new DataPoint(26, 0),
                new DataPoint(27, 0),
                new DataPoint(28, 0),
                new DataPoint(29, 0),
                new DataPoint(30, 0),
        });
        LineGraphSeries<DataPoint> series4 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 0),
                new DataPoint(2, 0),
                new DataPoint(3, 0),
                new DataPoint(4, 0),
                new DataPoint(5, 0), //
                new DataPoint(6, 0),
                new DataPoint(7, 0),
                new DataPoint(8, 0),
                new DataPoint(9, 0),
                new DataPoint(10, 0),
                new DataPoint(11, 0),
                new DataPoint(12, 0),
                new DataPoint(13, 0),
                new DataPoint(14, 0),
                new DataPoint(15, 0),
                new DataPoint(16, 0),
                new DataPoint(17, 0),
                new DataPoint(18, 0),
                new DataPoint(19, 0),
                new DataPoint(20, 0),
                new DataPoint(21, 0),
                new DataPoint(22, 0),
                new DataPoint(23, 0),
                new DataPoint(24, 0),
                new DataPoint(25, 0),
                new DataPoint(26, 0),
                new DataPoint(27, 0),
                new DataPoint(28, 0),
                new DataPoint(29, 0),
                new DataPoint(30, 0),
        });

        LineGraphSeries<DataPoint> series5 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 0),
                new DataPoint(2, 0),
                new DataPoint(3, 0),
                new DataPoint(4, 0),
                new DataPoint(5, 1), //
                new DataPoint(6, 0),
                new DataPoint(7, 0),
                new DataPoint(8, 0),
                new DataPoint(9, 0),
                new DataPoint(10, 0),
                new DataPoint(11, 0),
                new DataPoint(12, 0),
                new DataPoint(13, 0),
                new DataPoint(14, 0),
                new DataPoint(15, 0),
                new DataPoint(16, 0),
                new DataPoint(17, 0),
                new DataPoint(18, 0),
                new DataPoint(19, 0),
                new DataPoint(20, 0),
                new DataPoint(21, 0),
                new DataPoint(22, 0),
                new DataPoint(23, 0),
                new DataPoint(24, 0),
                new DataPoint(25, 0),
                new DataPoint(26, 0),
                new DataPoint(27, 0),
                new DataPoint(28, 0),
                new DataPoint(29, 0),
                new DataPoint(30, 0),
        });

        series.setTitle("personal");
        series.setColor(Color.rgb(255,0,0));
        tv_text_1.setTextColor(Color.rgb(255,0,0));

        series2.setTitle("personal2");
        series2.setColor(Color.rgb(0,255,0));
        tv_text_2.setTextColor(Color.rgb(0,255,0));

        series3.setTitle("personal3");
        series3.setColor(Color.rgb(0,0,255));
        tv_text_3.setTextColor(Color.rgb(0,0,255));

        series4.setTitle("personal4");
        series4.setColor(Color.rgb(0,0,0));
        tv_text_4.setTextColor(Color.rgb(0,0,0));

        series5.setTitle("personal5");
        series5.setColor(Color.rgb(255,0,250));
        tv_text_5.setTextColor(Color.rgb(255,0,250));

        graph.addSeries(series);
        graph.addSeries(series2);
        //graph.addSeries(series3);
        //graph.addSeries(series4);
        //graph.addSeries(series5);


        Viewport viewport = graph.getViewport();
        viewport.setXAxisBoundsManual(true);
        viewport.setYAxisBoundsManual(true);
        viewport.setMaxY(5);///////////////////
        viewport.setMinX(0);
        viewport.setMaxX(31);
        viewport.setScalable(true);
        viewport.setScrollable(true);
        return V;
    }

}