package com.kinotoday.kiev;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.Toast;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.kinotoday.kiev.model.Session;

import java.util.Locale;

public class MainActivity extends ActionBarActivity
{

  /**
   * The {@link android.support.v4.view.PagerAdapter} that will provide
   * fragments for each of the sections. We use a
   * {@link FragmentPagerAdapter} derivative, which will keep every
   * loaded fragment in memory. If this becomes too memory intensive, it
   * may be best to switch to a
   * {@link android.support.v4.app.FragmentStatePagerAdapter}.
   */
  SectionsPagerAdapter mSectionsPagerAdapter;

  /**
   * The {@link ViewPager} that will host the section contents.
   */
  ViewPager mViewPager;

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.
    mSectionsPagerAdapter = new SectionsPagerAdapter( getSupportFragmentManager() );

    Backendless.initApp( this, "C0CAF713-73D0-475A-FFED-43E5641FDD00", "007C7D0F-28CB-EE69-FFBC-120E8EB75E00", "v1" );

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById( R.id.pager );
    mViewPager.setAdapter( mSectionsPagerAdapter );
  }

  @Override
  public boolean onCreateOptionsMenu( Menu menu )
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate( R.menu.menu_main, menu );
    return true;
  }

  @Override
  public boolean onOptionsItemSelected( MenuItem item )
  {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if( id == R.id.action_settings )
    {
      return true;
    }

    return super.onOptionsItemSelected( item );
  }

  /**
   * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */
  public class SectionsPagerAdapter extends FragmentPagerAdapter
  {

    public SectionsPagerAdapter( FragmentManager fm )
    {
      super( fm );
    }

    @Override
    public Fragment getItem( int position )
    {
      // getItem is called to instantiate the fragment for the given page.
      // Return a PlaceholderFragment (defined as a static inner class below).
      return PlaceholderFragment.newInstance( position + 1 );
    }

    @Override
    public int getCount()
    {
      // Show 3 total pages.
      return 3;
    }

    @Override
    public CharSequence getPageTitle( int position )
    {
      Locale l = Locale.getDefault();
      switch( position )
      {
        case 0:
          return getString( R.string.title_section1 ).toUpperCase( l );
        case 1:
          return getString( R.string.title_section2 ).toUpperCase( l );
        case 2:
          return getString( R.string.title_section3 ).toUpperCase( l );
      }
      return null;
    }
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment
  {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance( int sectionNumber )
    {
      PlaceholderFragment fragment = new PlaceholderFragment();
      Bundle args = new Bundle();
      args.putInt( ARG_SECTION_NUMBER, sectionNumber );
      fragment.setArguments( args );
      return fragment;
    }

    public PlaceholderFragment()
    {
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
      final View rootView = inflater.inflate( R.layout.fragment_main, container, false );

      mRecyclerView = (RecyclerView) rootView.findViewById( R.id.sessions_view );

      // use this setting to improve performance if you know that changes
      // in content do not change the layout size of the RecyclerView
      mRecyclerView.setHasFixedSize( true );

      // use a linear layout manager
      mLayoutManager = new LinearLayoutManager( getActivity() );
      mRecyclerView.setLayoutManager( mLayoutManager );

      BackendlessDataQuery query = new BackendlessDataQuery();
      query.setPageSize( 50 );
      QueryOptions queryOptions = new QueryOptions();
      queryOptions.setRelationsDepth( 2 );
      query.setQueryOptions( queryOptions );
      Backendless.Data.of( Session.class ).find( query, new AsyncCallback<BackendlessCollection<Session>>()
      {
        @Override
        public void handleResponse( BackendlessCollection<Session> response )
        {
          // specify an adapter (see also next example)
          mAdapter = new SessionAdapter( rootView.getContext(), response.getCurrentPage() );
          mRecyclerView.setAdapter( mAdapter );
//          mAdapter.notifyDataSetChanged();
        }

        @Override
        public void handleFault( BackendlessFault fault )
        {
          Toast.makeText( rootView.getContext(), fault.getMessage(), Toast.LENGTH_LONG ).show();
        }
      } );

      return rootView;
    }
  }
}
