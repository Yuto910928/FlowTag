# FlowTag
这个FlowTag是对[FlowTag](https://github.com/hanhailong/FlowTag)功能的添加，增加了1种FLOW_TAG_FIRSTVIEW_CHECKED_SINGLE_OTHER_CHECKED_MULTI模式
当选择这个模式后，选择第一个标签则取消其他标签的选中，当选中其他标签时第一个标签取消，其他标签可多选
## 实现效果图如下:
![Alt text](http://cdn.sinacloud.net/yuto910928-github/FlowTag/FlowTag.png?KID=sina,1h06y4ze4EatlAJE7wH0&Expires=1471921691&ssig=2U0WHFnMlT)

## 实现代码：
### 1.在xml中添加FlowTagLayout
```
  <com.lv.yuto.library.FlowTagLayout
                android:id="@+id/color_flow_layout"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
```
### 2.创建子布局tag_item.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content">

    <TextView
        android:id="@+id/tv_tag"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:layout_marginTop="10dp"
        android:background="@drawable/round_rectangle_bg"
        android:paddingBottom="5dp"
        android:paddingLeft="10dp"
        android:paddingRight="10dp"
        android:paddingTop="5dp"
        android:text="TAG标签"
        android:textColor="@drawable/normal_text_color" />

</LinearLayout>
```
### 3.实现一个基本Bean类
```
public class Tag {
    public int id;
    public String name;
    public boolean isSelected;

    public Tag(int id, String name, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```
### 4.添加TagAdapter继承BaseTagAdapter
```
public class TagAdapter extends BaseTagAdapter<Tag> {

    public TagAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=View.inflate(mContext,R.layout.tag_item,null);
            holder=new ViewHolder();
            holder.name= (TextView) view.findViewById(R.id.tv_tag);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
            holder.name.setText(mDataList.get(i).name);
        return view;
    }

    @Override
    public boolean isSelectedPosition(int position) {
        return  mDataList.get(position).isSelected;
    }
    public static class ViewHolder{
        TextView name;
    }
}
```
### 5.代码实现以Activity为例
```
public class MainActivity extends AppCompatActivity {

    private FlowTagLayout mColorFlowTagLayout;
    private FlowTagLayout mSizeFlowTagLayout;
    private FlowTagLayout mMobileFlowTagLayout;
    private FlowTagLayout mAreaFlowTagLayout;
    private TagAdapter mSizeTagAdapter;
    private TagAdapter mColorTagAdapter;
    private TagAdapter mMobileTagAdapter;
    private TagAdapter mAreaTagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mColorFlowTagLayout = (FlowTagLayout) findViewById(R.id.color_flow_layout);
        mSizeFlowTagLayout = (FlowTagLayout) findViewById(R.id.size_flow_layout);
        mMobileFlowTagLayout = (FlowTagLayout) findViewById(R.id.mobile_flow_layout);
        mAreaFlowTagLayout = (FlowTagLayout) findViewById(R.id.area_flow_layout);

        //颜色
        mColorTagAdapter = new TagAdapter(this);
        mColorFlowTagLayout.setAdapter(mColorTagAdapter);
        mColorFlowTagLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                Toast.makeText(MainActivity.this, "颜色:" + parent.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
            }
        });

        //尺寸
        mSizeTagAdapter = new TagAdapter(this);
        mSizeFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mSizeFlowTagLayout.setAdapter(mSizeTagAdapter);
        mSizeFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    Toast.makeText(MainActivity.this, "移动研发:" + sb.toString(), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this, "没有选择标签:", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //移动研发标签
        mMobileTagAdapter = new TagAdapter(this);
        mMobileFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        mMobileFlowTagLayout.setAdapter(mMobileTagAdapter);
        mMobileFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();

                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    Toast.makeText(MainActivity.this, "移动研发:" + sb.toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "没有选择标签:", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //地区标签
        mAreaTagAdapter = new TagAdapter(this);
        mAreaFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_FIRSTVIEW_CHECKED_SINGLE_OTHER_CHECKED_MULTI);
        mAreaFlowTagLayout.setAdapter(mAreaTagAdapter);
        mAreaFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();

                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    Toast.makeText(MainActivity.this, "地区:" + sb.toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "没有选择标签:", Toast.LENGTH_SHORT).show();
                }
            }
        });
        initColorData();

        initSizeData();

        initMobileData();

        initAreaData();
    }

    private void initAreaData() {
        List<Tag> dataSource = new ArrayList<>();
        dataSource.add(new Tag(0,"不限",true));
        dataSource.add(new Tag(1,"大陆",false));
        dataSource.add(new Tag(2,"港澳台",false));
        dataSource.add(new Tag(3,"欧美",false));
        dataSource.add(new Tag(4,"日韩",true));
        dataSource.add(new Tag(5,"新加坡",false));
        dataSource.add(new Tag(6,"马来西亚",false));
        dataSource.add(new Tag(7,"泰国",false));
        dataSource.add(new Tag(8,"其他",false));
        mAreaTagAdapter.onlyAddAll(dataSource);
    }

    private void initMobileData() {
        List<Tag> dataSource = new ArrayList<>();
        dataSource.add(new Tag(0,"android",true));
        dataSource.add(new Tag(1,"安卓",false));
        dataSource.add(new Tag(2,"SDK源码",false));
        dataSource.add(new Tag(3,"IOS",false));
        dataSource.add(new Tag(4,"iPhone",false));
        dataSource.add(new Tag(5,"游戏",true));
        dataSource.add(new Tag(6,"fragment",false));
        dataSource.add(new Tag(7,"viewcontroller",false));
        dataSource.add(new Tag(8,"cocoachina",false));
        dataSource.add(new Tag(8,"移动研发工程师",false));
        dataSource.add(new Tag(9,"移动互联网",true));
        dataSource.add(new Tag(10,"高薪+期权",false));
        mMobileTagAdapter.onlyAddAll(dataSource);
    }

    private void initColorData() {
        List<Tag> dataSource = new ArrayList<>();
        dataSource.add(new Tag(0,"红色",false));
        dataSource.add(new Tag(1,"黑色",false));
        dataSource.add(new Tag(2,"花边色",false));
        dataSource.add(new Tag(3,"深蓝色",false));
        dataSource.add(new Tag(4,"白色",false));
        dataSource.add(new Tag(5,"玫瑰红色",true));
        dataSource.add(new Tag(7,"葡萄红色",false));
        dataSource.add(new Tag(8,"屎黄色",false));

        mColorTagAdapter.onlyAddAll(dataSource);
    }

    /**
     * 初始化数据
     */
    private void initSizeData() {
        List<Tag> dataSource = new ArrayList<>();
        dataSource.add(new Tag(0,"28 (2.1尺)",false));
        dataSource.add(new Tag(1,"29 (2.2尺)",false));
        dataSource.add(new Tag(2,"30 (2.3尺)",false));
        dataSource.add(new Tag(3,"31 (2.4尺)",false));
        dataSource.add(new Tag(4,"32 (2.5尺)........",false));
        dataSource.add(new Tag(5,"33 (2.6尺)",false));
        dataSource.add(new Tag(6,"34 (2.7尺)",false));
        dataSource.add(new Tag(7,"35 (2.8尺)",false));
        dataSource.add(new Tag(8,"36 (2.9尺)",false));
        dataSource.add(new Tag(8,"37 (3.0尺)",false));
        dataSource.add(new Tag(9,"38 (3.1尺)",false));
        mSizeTagAdapter.onlyAddAll(dataSource);
    }



}
```
