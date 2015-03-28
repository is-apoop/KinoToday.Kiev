package kino.today.model;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class Screenshot
{
  private String objectId;
  private String ownerId;
  private java.util.Date updated;
  private String link;
  private java.util.Date created;

  public String getObjectId()
  {
    return objectId;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getLink()
  {
    return link;
  }

  public void setLink( String link )
  {
    this.link = link;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

                                                    
  public Screenshot save()
  {
    return Backendless.Data.of( Screenshot.class ).save( this );
  }

  public Future<Screenshot> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Screenshot> future = new Future<Screenshot>();
      Backendless.Data.of( Screenshot.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Screenshot> callback )
  {
    Backendless.Data.of( Screenshot.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Screenshot.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( Screenshot.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Screenshot.class ).remove( this, callback );
  }

  public static Screenshot findById( String id )
  {
    return Backendless.Data.of( Screenshot.class ).findById( id );
  }

  public static Future<Screenshot> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Screenshot> future = new Future<Screenshot>();
      Backendless.Data.of( Screenshot.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Screenshot> callback )
  {
    Backendless.Data.of( Screenshot.class ).findById( id, callback );
  }

  public static Screenshot findFirst()
  {
    return Backendless.Data.of( Screenshot.class ).findFirst();
  }

  public static Future<Screenshot> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Screenshot> future = new Future<Screenshot>();
      Backendless.Data.of( Screenshot.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Screenshot> callback )
  {
    Backendless.Data.of( Screenshot.class ).findFirst( callback );
  }

  public static Screenshot findLast()
  {
    return Backendless.Data.of( Screenshot.class ).findLast();
  }

  public static Future<Screenshot> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Screenshot> future = new Future<Screenshot>();
      Backendless.Data.of( Screenshot.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Screenshot> callback )
  {
    Backendless.Data.of( Screenshot.class ).findLast( callback );
  }

  public static BackendlessCollection<Screenshot> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Screenshot.class ).find( query );
  }

  public static Future<BackendlessCollection<Screenshot>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Screenshot>> future = new Future<BackendlessCollection<Screenshot>>();
      Backendless.Data.of( Screenshot.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Screenshot>> callback )
  {
    Backendless.Data.of( Screenshot.class ).find( query, callback );
  }
}