package com.veinhorn.rwbytickets;

import android.app.Activity;
import android.content.Intent;
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
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.veinhorn.rwbytickets.auth.creds.ICredentials;
import com.veinhorn.rwbytickets.tickets.TicketsActivity;

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
        PrimaryDrawerItem primaryItem = new PrimaryDrawerItem().withName("Purchase")
                .withIcon(FontAwesome.Icon.faw_shopping_cart);
        SecondaryDrawerItem secondaryItem = new SecondaryDrawerItem().withName("Tickets")
                .withIcon(FontAwesome.Icon.faw_ticket);

        return new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(createAccountHeader())
                .addDrawerItems(primaryItem, secondaryItem)
                .withFooterClickable(false)
                .addDrawerItems()
                // TODO: Create different listeners for different drawers
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 2:
                                Toast.makeText(activity, "Clicked nav drawer item", Toast.LENGTH_SHORT).show();
                                activity.startActivity(new Intent(activity, TicketsActivity.class));
                                return true;
                        }
                        return false;
                    }
                })
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
                        return true;
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
