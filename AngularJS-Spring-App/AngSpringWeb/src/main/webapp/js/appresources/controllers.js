//Controller when the main page/view loads
userApp.controller('homeController',['$scope','$location',function($scope,$location){
	$scope.welcomeMessage = "This the homepage of this demo AngularJS Spring Integration app. There are links as shown in the top navigation. Click on the links in navigation bar, and you would see different views loading without the whole page being loaded. Only the views get changed.";
}]);

//Controller for All Products View
userApp.controller('productListCntrl',['$scope','$location','userservice',function($scope, $location,userservice){
	userservice.getProducts($scope);
			
}]);

//Controller for New Product View
userApp.controller('newProductCntrl',['$scope','userservice',function($scope,userservice){
	
	$scope.addNewProduct = function(){
		var product = {
				'pId':$scope.pId,
				'name' : $scope.name,
				'owner' : $scope.owner,
				'price' : $scope.price
			};
		userservice.addProduct(product, $scope);
		$scope.pId="";
		$scope.name="";
		$scope.owner="";
		$scope.price="";
	};
	
}]);

//Controller for Individual User View
userApp.controller('productByIdCntrl',['$scope','userservice','$routeParams',function($scope,userservice,$routeParams){
	userservice.getProduct($routeParams.pId,$scope);
}]);

