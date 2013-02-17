<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href='<c:url value="/resources/Content/Site.css"/>' rel="stylesheet" type="text/css" />
    <link href='<c:url value="/resources/Content/themes/redmond/jquery-ui-1.8.16.custom.css"/>' rel="stylesheet"
        type="text/css" />
    <!-- jTable style file -->
    <link href='<c:url value="/resources/Scripts/jtable/themes/standard/blue/jtable_blue.css"/>' rel="stylesheet"
        type="text/css" />
    <script src='<c:url value="/resources/Scripts/jquery-1.6.4.min.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/Scripts/jquery-ui-1.8.16.custom.min.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/Scripts/modernizr-1.7.min.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/Scripts/jtablesite.js"/>' type="text/javascript"></script>
    <!-- A helper library for JSON serialization -->
    <script type="text/javascript" src='<c:url value="/resources/Scripts/jtable/external/json2.js"/>'></script>
    <!-- Core jTable script file -->
    <script type="text/javascript" src='<c:url value="/resources/Scripts/jtable/jquery.jtable.js"/>'></script>
    <!-- ASP.NET Web Forms extension for jTable -->
    <script type="text/javascript" src='<c:url value="/resources/Scripts/jtable/extensions/jquery.jtable.aspnetpagemethods.js"/>'></script>

    <style>
        .child-opener-image
        {
            cursor: pointer;
        }
        .child-opener-image-column
        {
            text-align: center;
        }
        .jtable-dialog-form
        {
            min-width: 220px;
        }
        .jtable-dialog-form input[type="text"]
        {
            min-width: 200px;
        }
    </style>
</head>
<body>
<div id="PersonTable" style="width: 580px; margin: auto;"></div>

<script type="text/javascript">

    $(document).ready(function () {
        //Prepare jtable plugin
        $('#PersonTable').jtable({
            title: 'The Person List',
            paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10)
            //sorting: true, //Enable sorting
            //selecting: true, //Enable selecting
            //multiselect: true, //Allow multiple selecting
            //defaultSorting: 'firstName ASC', //Set default sorting
            actions: {
                listAction: '<c:url value="/main/record/crud"/>',//REST CALL
                createAction: '<c:url value="/main/record/add"/>',
                updateAction: '<c:url value="/main/record/edit"/>',
                deleteAction: '<c:url value="/main/record/delete"/>'
            },
            fields: {
                id: {
                    key: true,
                    create: false,
                    edit: false,
                    list: false
                },
                //CHILD TABLE DEFINITION FOR "CreditCard" 
                CreditCard: { 
                    title: '', 
                    width: '1%', 
                    sorting: false, 
                    edit: false, 
                    create: false, 
                    display: function (personData) { 
                        //Create an image that will be used to open child table 
                        var $img = $('<img src=<c:url value="/resources/Content/images/Misc/creditcard.png"/> title="Add Credit Cards" height="22px" width="24"/>'); 
                        //Open child table when user clicks the image 
                        $img.click(function () { 
                            $('#PersonTable').jtable('openChildTable', 
                                    $img.closest('tr'), 
                                    { 
                                        title: personData.record.firstName + ' - Credit Cards', 
                                        actions: { 
                                            listAction: '<c:url value="/main/creditcard/listCreditCard?id="/>' + personData.record.id, 
                                            deleteAction: '<c:url value="/main/creditcard/deleteCreditCard"/>', 
                                            updateAction: '<c:url value="/main/creditcard/listCreditCard?id="/>' + personData.record.id, 
                                            createAction: '<c:url value="/main/creditcard/addCreditCard?id="/>' + personData.record.id
                                            
                                        }, 
                                        fields: { 
                                        	creditId: { 
                                                key: true, 
                                                create: false, 
                                                edit: false, 
                                                list: false
                                            }, 
                                            type: { 
                                                title: 'Card Type', 
                                                width: '30%', 
                                                options: { 'Visa': 'Visa', 'Master': 'Master', 'American': 'American','Discover':'Discover' } 
                                            }, 
                                            number: { 
                                                title: 'Number', 
                                                width: '30%'
                                            }
                                        } 
                                    }, function (data) { //opened handler 
                                        data.childTable.jtable('load'); 
                                    }); 
                        }); 
                        //Return image to show on the person row 
                        return $img; 
                    } 
                }, 
                firstName: {
                    title: 'First Name',
                    width: '15%'
                },
                lastName: {
                    title: 'Last Name',
                    width: '15%'
                },
                money: {
                    title: 'Money $$',
                    width: '12%',
                }
                
            }
        });
        //Load person list from server
        $('#PersonTable').jtable('load');
    });

</script>
</body>
</html>