--GET PRODUCTS DATA TO DISPLAY
--GetAll include paging
SELECT productID, productName, productImage, productDescription, productPrice, createDate,categoryID
FROM(
	SELECT ROW_NUMBER() OVER (ORDER BY CONVERT(DATE, createDate) DESC) AS i, *
	FROM tblProduct
	WHERE quantity > 0 AND [status] = 1
) AS x
WHERE i BETWEEN 1 and 5

SELECT COUNT(productID) as total
FROM tblProduct
WHERE quantity > 0 AND [status] = 1

---------------------------------------------------------------------------------
--searchByName include paging

SELECT productID, productName, productImage, productDescription, productPrice, createDate
FROM (
	SELECT ROW_NUMBER() OVER (ORDER BY CONVERT(DATE, createDate) DESC) AS i, *
	FROM tblProduct
	WHERE quantity > 0 AND [status] = 1 AND productName LIKE '%C%'
) AS x
WHERE i BETWEEN 1 and 5

SELECT COUNT(productID) as total
FROM tblProduct
WHERE quantity > 0 AND [status] = 1 AND productName LIKE '%C%'

-------------------------------------------------------------------------------------
--Search By Category include Paging
SELECT productID as id, productName as name, productImage as img, productDescription as [des], productPrice as price
FROM (
	SELECT ROW_NUMBER() OVER (ORDER BY CONVERT(DATE, createDate) DESC) AS i, *
	FROM tblProduct
	WHERE quantity > 0 AND [status] = 1 AND categoryID = 'Co'
) AS x
WHERE i BETWEEN 1 and 5

SELECT COUNT(productID) as total
FROM tblProduct
WHERE quantity > 0 AND [status] = 1 AND categoryID = 'Co'

--------------------------------------------------------------------------------------------------------

--searchByPrice include paging

SELECT productID, productName, productImage, productDescription, productPrice, createDate
FROM (
	SELECT ROW_NUMBER() OVER (ORDER BY CONVERT(DATE, createDate) DESC) AS i, *
	FROM tblProduct
	WHERE quantity > 0 AND [status] = 1 AND productPrice BETWEEN 30000 AND 300000
) AS x
WHERE i BETWEEN 1 and 5

SELECT COUNT(productID) as total
FROM tblProduct
WHERE quantity > 0 AND [status] = 1 AND productPrice BETWEEN 10000 AND 30000
GO

----------------------------------------------------------------------------------------------------------------------
--Prepare trigger for ordering

--trigger check not admin on tblOrder
USE [HanaShopDB]
GO

/****** Object:  Trigger [dbo].[trg_check_user_to_order]    Script Date: 1/17/2021 12:32:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[trg_check_user_to_order] 
ON [dbo].[tblOrder] 
AFTER INSERT, UPDATE
AS 
BEGIN
BEGIN TRANSACTION  
 Declare @userID VARCHAR(20)
 Declare @isAdmin bit
 Select @userID = [userID] from inserted
 Select @isAdmin = [isAdmin] from [dbo].[tblUser] where [userID] = @userID
	If (@isAdmin = 0)
		Begin 
			COMMIT TRANSACTION
		End
	ELSE
		Begin
			PRINT 'Admin cannot do order';
			ROLLBACK TRANSACTION
		End
 END

GO

--INSERT order steps:
--1.Insert values to tblOrder (orderID..auto, userID, payment_success = default(0), orderDate = default)
--2.UPDATE_order_steps
--3.Check out: Update value of column payment_success on tblOrder

--UPDATE_order_steps:
--1.Insert/Update/Delete values to tblOrderDetails
--(ID..auto, orderID, productID, quantity, price)
--2.Update value of column totalPrice on tblOrder
--3.Update value of column quantity on tblProduct

--CREATE TRIGGER ON tblOrderDetails FOR INSERT/UPDATE/DELETE:
----check: productID must active, quantity must available (accepted DELETE)
----AUTO: Update values: totalPrice on tblOrder, quantity on tblProduct


 --TRIGGER ADD NEW ITEM TO ORDER_DETAILS
	----check: productID must active, quantity must available
----AUTO: Update values: totalPrice on tblOrder, quantity on tblProduct

GO
ALTER TRIGGER [dbo].[trg_insert_another_product_to_order] 
ON [dbo].[tblOrderDetails]
AFTER INSERT
AS 
BEGIN
BEGIN TRANSACTION  
 Declare @productID VARCHAR(20)
 Declare @quantity int
 Declare @orderID varchar(40)
 Declare @available_quantity int
 Declare @status bit
 Declare @unit_price decimal(19,4)
 Declare @pro_name nvarchar(100)
 Declare @total_price decimal(19,4)

 Select @productID = [productID] from inserted

 Select @orderID = [orderID] from inserted

 Select @quantity = [quantity] from inserted 

 Select @available_quantity = p.[quantity] 
 from [dbo].[tblProduct] p join inserted 
 on p.[productID] = inserted.[productID] 

 Select @status = p.[status] 
 from [dbo].[tblProduct] p join inserted 
 on p.[productID] = inserted.[productID]

 Select @unit_price = p.[productPrice]
 from [dbo].[tblProduct] p join inserted 
 on p.[productID] = inserted.[productID]

 Select @pro_name = p.[productName]
 from [dbo].[tblProduct] p join inserted 
 on p.[productID] = inserted.[productID]

 Select @total_price = (
 Select SUM(od.price) as 'Total'
 from tblOrderDetails od
 where od.orderID = @orderID
)


	If (@status = 1 AND @quantity > 0 AND @available_quantity >= @quantity)
		Begin 
			--update totalPrice on tblOrder: old_total + inserted items
			UPDATE [dbo].[tblOrder]
			SET [totalPrice] = @total_price 
			WHERE [orderID] = @orderID

			--update quantity on tblProduct: old_quantity - inserted quantity
			UPDATE [dbo].[tblProduct]
			SET [quantity] = @available_quantity - @quantity
			WHERE [productID] = @productID
			COMMIT TRANSACTION
		End
	ELSE
		Begin
			PRINT CAST(@pro_name AS NVARCHAR(15)) + ' :' + CAST(@available_quantity AS NVARCHAR(15)) + ' available. ERROR quantity of your order! Try again.';
			ROLLBACK TRANSACTION
		End

 END

GO

--TRIGGER UPDATE PRODUCT QUANTITY TO ORDER_DETAILS
	----check: productID must active, quantity must available
----AUTO: Update values: totalPrice on tblOrder, quantity on tblProduct

GO
ALTER TRIGGER [dbo].[trg_update_product_quantity_to_order] 
ON [dbo].[tblOrderDetails]
AFTER UPDATE
AS 
BEGIN
BEGIN TRANSACTION  
 Declare @productID VARCHAR(20)
 Declare @quantity int
 Declare @orderID varchar(40)
 Declare @available_quantity int
 Declare @status bit
 Declare @pro_name nvarchar(100)
 Declare @old_price decimal(19,4)
 Declare @new_price decimal(19,4)
 Declare @total_price decimal(19,4)

 Select @productID = [productID] from inserted

 Select @orderID = [orderID] from inserted

 Select @quantity = [quantity] from inserted 

 Select @old_price = [price] from deleted

 Select @new_price = [price] from inserted

 Select @available_quantity = p.[quantity] 
 from [dbo].[tblProduct] p join inserted 
 on p.[productID] = inserted.[productID] 

 Select @status = p.[status] 
 from [dbo].[tblProduct] p join inserted 
 on p.[productID] = inserted.[productID]

 Select @total_price = (
 Select SUM(od.price) as 'Total'
 from tblOrderDetails od
 where od.orderID = @orderID
)


 Select @pro_name = p.[productName]
 from [dbo].[tblProduct] p join inserted 
 on p.[productID] = inserted.[productID]

	If (@status = 1 AND @quantity > 0 AND @available_quantity >= @quantity)
		Begin 
			--update totalPrice on tblOrder:
			UPDATE [dbo].[tblOrder]
			SET [totalPrice] = @total_price
			WHERE [orderID] = @orderID

			--update quantity on tblProduct: available + old_quantity - inserted quantity
			UPDATE [dbo].[tblProduct]
			SET [quantity] = (select @available_quantity - @quantity + [quantity] from deleted)
			WHERE [productID] = @productID
			COMMIT TRANSACTION
		End
	ELSE
		Begin
			PRINT CAST(@pro_name AS NVARCHAR(15)) + ' :' + CAST(@available_quantity AS NVARCHAR(15)) + ' available. ERROR quantity of your order! Try again.';
			ROLLBACK TRANSACTION
		End

 END

GO

--TRIGGER DELETE PRODUCT QUANTITY TO ORDER_DETAILS
	----check: delete quantity of order_Details
----AUTO: Update values: totalPrice on tblOrder, quantity on tblProduct

GO
ALTER TRIGGER [dbo].[trg_delete_product_from_order] 
ON [dbo].[tblOrderDetails]
AFTER DELETE
AS 
BEGIN
BEGIN TRANSACTION  
 Declare @del_productID VARCHAR(20)
 Declare @del_quantity int
 Declare @orderID varchar(40)
 Declare @available_quantity int
 Declare @status bit
 Declare @pro_name nvarchar(100)
 Declare @old_price decimal(19,4)
 Declare @total_price decimal(19,4)

 Select @del_productID = [productID] from deleted

 Select @orderID = [orderID] from deleted

 Select @del_quantity = [quantity] from deleted 

 Select @old_price = [price] from deleted

 Select @available_quantity = [quantity] 
 from [dbo].[tblProduct] 
 where productID = @del_productID

 Select @total_price = (
 Select SUM(od.price) as 'Total'
 from tblOrderDetails od
 where od.orderID = @orderID
)
 Select @pro_name = p.[productName]
 from [dbo].[tblProduct] p join deleted 
 on p.[productID] = deleted.[productID]

	If (@del_quantity > 0)
		Begin 
			--update totalPrice on tblOrder:
			UPDATE [dbo].[tblOrder]
			SET [totalPrice] = @total_price
			WHERE [orderID] = @orderID

			--update quantity on tblProduct: available + old_quantity - inserted quantity
			UPDATE [dbo].[tblProduct]
			SET [quantity] = (select @available_quantity + @del_quantity from deleted)
			WHERE [productID] = @del_productID
			PRINT 'Deleted!';
			COMMIT TRANSACTION
		End
	ELSE
		Begin
			PRINT CAST(@pro_name AS NVARCHAR(15)) + ' is not found in your order!';
			ROLLBACK TRANSACTION
		End

 END

GO
----------------------------------------------------------------------------------------

 --Create new Order
insert into tblOrder(orderID, userID)
values('test', 'test')

--delete order
DELETE FROM [dbo].[tblOrderDetails] WHERE ID= 'test1';

DELETE FROM [dbo].[tblOrder] WHERE orderID = 'test';

--Insert new product to cart

insert into tblOrderDetails(ID, orderID, productID, quantity, price)
values('auto', 'test', 'Ca222', 1, 40000)

--Update quantity of product from cart
Update tblOrderDetails
set quantity = 1, price = 100000 (get this from controller)
where ID = 'test1'

--Delete product from cart
DELETE FROM [dbo].[tblOrderDetails] WHERE ID = 'test1'

SELECT *
FROM (
SELECT ROW_NUMBER() OVER (ORDER BY CONVERT(DATE, createDate) DESC) AS i, *
FROM tblProduct) as x


insert into tblProduct (productID, productName, productImage, productDescription, productPrice, createDate, userUpdate, quantity, [status])
values('test1', 'test', 'https://www.outbrain.com/techblog/wp-content/uploads/2017/05/road-sign-361513_960_720.jpg', 'TEST', '10000', getdate(), 'test', '100', 1)
go
ALTER TABLE [dbo].[tblProduct] ADD  CONSTRAINT [df_last_update]  DEFAULT (getdate()) FOR [lastUpdate]
GO

select * from tblProduct where productID = 'test1'

select * from tblProductHasCategory

insert into tblProductHasCategory
values('test1', 'Co')

UPDATE tblProductHasCategory
SET categoryID = 'Ca'
WHERE productID = 'test1'


SELECT categoryID
FROM tblProductHasCategory
WHERE productID = 'test1'

UPDATE tblProduct
SET lastUpdate = getDate(), userUpdate = 'test', [status] = 0
WHERE productID = 'test1'
select * from [tblProductHasCategory] 

DELETE FROM [tblProductHasCategory] 
WHERE productID = 'test21222'

DELETE FROM tblProduct
WHERE productID = 'test1'

select* from tblProduct

-----------------------------------END SHOPPING : ĐÙAAA HẾT TIỀN R BỎ QUA SHOPPING  :<< -----------------------------
--BEST SELLER

go
select productID, SOLD from (
select ROW_NUMBER() OVER (ORDER BY SUM(od.quantity) DESC) AS i, productID, SUM(od.quantity) as 'SOLD'
from 
tblOrderDetails od join tblOrder o
on od.orderID = o.orderID
WHERE o.payment_success = 1 
GROUP BY productID
) as x WHERE x.i = 1



-------------------------------------------------CREATE FUNCTION TO FIND BEST SELLER IN RECENT MONTH
GO
create function findBestSeller()
RETURNS @table TABLE (productID varchar(20), Sold int)
as
begin
declare @productID varchar(20),
@Date date = getdate(),@Month int, @Year int
set @Month = MONTH(@Date)
set @Year = YEAR(@Date)
insert into @table
select x.productID, SOLD from (
select ROW_NUMBER() OVER (ORDER BY SUM(od.quantity) DESC) AS i, productID, SUM(od.quantity) as 'SOLD'
from 
tblOrderDetails od join tblOrder o
on od.orderID = o.orderID
WHERE o.payment_success = 1 
AND YEAR(o.orderDate) = @Year
AND MONTH(o.orderDate) = @Month
GROUP BY productID
) as x WHERE x.i = 1

return 
end
go

SELECT *
FROM  findBestSeller()
go
--------------------------------------------------------------------------------------------------------------------------------
