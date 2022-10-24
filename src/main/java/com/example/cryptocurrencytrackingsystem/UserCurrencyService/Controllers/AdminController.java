package com.example.cryptocurrencytrackingsystem.UserCurrencyService.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cryptocurrencytrackingsystem.Database.Service.DataServiceInterface;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsUsers;

@Controller
@RequestMapping( "/admin" )
public class AdminController
{

    private DataServiceInterface dataService;

    @Autowired
    public void setDataServiceDAO( @Qualifier( "dataService" ) DataServiceInterface dataService )
    {
        this.dataService = dataService;
    }

    @GetMapping( "/showUsersTable" )
    public String showTable( Model theModel, @RequestParam( required = false ) String sort )
    {
        theModel.addAttribute( "usersData", getSortedList( sort ) );
        return "Admin/usersDataTable";
    }

    @GetMapping( "/showTableToRemove" )
    public String showTableToRemove( Model theModel, @RequestParam( required = false ) String sort )
    {
        theModel.addAttribute( "usersData", getSortedList( sort ) );
        return "Admin/removeUserTable";
    }

    @GetMapping( "/deleteAnAccount" )
    public String deleteAnAccount( Model theModel, @RequestParam( "userId" ) Integer userId )
    {

        dataService.deleteAnAccount( userId );
        theModel.addAttribute( "usersData", dataService.getUsers( SortUtilsUsers.id_sort ) );
        return "Admin/removeUserTable";
    }

    private List< User > getSortedList( String sort )
    {
        List< User > theCustomers;
        if( sort != null )
        {
            int theSortField = Integer.parseInt( sort );
            theCustomers = dataService.getUsers( theSortField );
        }
        else
        {
            theCustomers = dataService.getUsers( 0 );
        }
        return theCustomers;
    }

}
