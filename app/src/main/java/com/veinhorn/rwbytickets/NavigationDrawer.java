package com.veinhorn.rwbytickets;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.veinhorn.rwbytickets.auth.creds.ICredentials;

/**
 * Created by veinhorn on 11.5.16.
 */
public class NavigationDrawer {
    private Activity activity;
    private Toolbar toolbar;
    private ICredentials credentials;

    public NavigationDrawer(Activity activity, Toolbar toolbar, ICredentials credentials) {
        this.activity = activity;
        this.toolbar = toolbar;
        this.credentials = credentials;
    }

    public Drawer create() {
        PrimaryDrawerItem primaryItem = new PrimaryDrawerItem().withName("Purchase");
        SecondaryDrawerItem secondaryItem = new SecondaryDrawerItem().withName("Tickets");

        return new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(createAccountHeader())
                .addDrawerItems(primaryItem, secondaryItem)
                .withFooterClickable(false)
                .addDrawerItems()
                .build();
    }

    private AccountHeader createAccountHeader() {
        return new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header_background)
                .addProfiles(createProfileDrawerItem())
                .withDividerBelowHeader(true)
                .withAlternativeProfileHeaderSwitching(false)
                .withSelectionListEnabled(false)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        Toast.makeText(activity, "Clicked on user profile", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();
    }

    private ProfileDrawerItem createProfileDrawerItem() {
        return new ProfileDrawerItem()
                .withName(credentials.getLogin())
                .withIcon(FontAwesome.Icon.faw_user);
    }
}
