package com.kinotoday.kiev;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.kinotoday.kiev.model.Session;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolder>
{
  private Context context;
  private List<Session> mSessions;

  public SessionAdapter( Context context, List<Session> mSessions )
  {
    this.context = context;
    this.mSessions = mSessions;
  }

  // Create new views (invoked by the layout manager)
  @Override
  public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType )
  {
    // create a new view
    View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_session, parent, false );

    return new ViewHolder( v );
  }

  // Replace the contents of a view (invoked by the layout manager)
  @Override
  public void onBindViewHolder( ViewHolder holder, final int position )
  {
    // - get element from your dataset at this position
    // - replace the contents of the view with that element
    holder.sessionTitle.setText( mSessions.get( position ).getMovie().getTitle() );
    holder.purchaseButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View v )
      {
        String url = mSessions.get( position ).getTicketsLink();
        ;
        Intent i = new Intent( Intent.ACTION_VIEW );
        i.setData( Uri.parse( url ) );
        context.startActivity( i );
      }
    } );
  }

  @Override
  public int getItemCount()
  {
    return mSessions.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder
  {
    public TextView sessionTitle;
    public Button purchaseButton;

    public ViewHolder( View sessionView )
    {
      super( sessionView );

      this.sessionTitle = (TextView) sessionView.findViewById( R.id.session_title );
      this.purchaseButton = (Button) sessionView.findViewById( R.id.purchaseButton );
    }
  }
}
