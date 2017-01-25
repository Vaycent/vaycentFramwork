package Helper;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class ViewPageAdapter extends PagerAdapter
	{

		List<ImageView> list = null;
		public ViewPageAdapter(List<ImageView> _list)
		{
			list = _list;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			//super.destroyItem(container, position, object);
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// return super.instantiateItem(container, position);
			// 修改position
			position = position % list.size();
			// 将图片控件添加到容器
			container.addView(list.get(position));

			// 返回
			return list.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
//			return list.size();
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

}