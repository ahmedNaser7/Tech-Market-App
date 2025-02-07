package com.example.techmarket.core.navigation



import CartScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.techmarket.latech.domain.model.Product
import com.example.techmarket.latech.presentation.account.AccountInformationScreen
import com.example.techmarket.latech.presentation.account.ProfileScreen
import com.example.techmarket.latech.presentation.account.ProfileViewModel
import com.example.techmarket.latech.presentation.admin.AdminDashboardScreen
import com.example.techmarket.latech.presentation.admin.AdminViewModel
import com.example.techmarket.latech.presentation.auth.LoginScreen
import com.example.techmarket.latech.presentation.auth.RegisterScreen
import com.example.techmarket.latech.presentation.auth.components.viewModel.LoginViewModel
import com.example.techmarket.latech.presentation.cart.CartViewModel
import com.example.techmarket.latech.presentation.home.HomeNavType
import com.example.techmarket.latech.presentation.home.HomeScreen
import com.example.techmarket.latech.presentation.home.HomeViewModel
import com.example.techmarket.latech.presentation.onBoarding.OnBoardingScreen
import com.example.techmarket.latech.presentation.onBoarding.components.viewModel.OnBoardingViewModel
import com.example.techmarket.latech.presentation.product.ProductScreen
import com.example.techmarket.latech.presentation.product.ProductViewModel
import com.example.techmarket.latech.presentation.search.SearchScreen
import com.example.techmarket.latech.presentation.search.SearchViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.typeOf


@Composable
fun TechMarketNavigationBar(
    navController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel = koinViewModel(),
    loginViewModel: LoginViewModel = koinViewModel(),
    searchViewModel: SearchViewModel = koinViewModel(),
    homeViewModel: HomeViewModel = koinViewModel(),
    adminViewModel: AdminViewModel= koinViewModel(),
    productViewModel: ProductViewModel = koinViewModel(),
    cartViewModel: CartViewModel = koinViewModel(),
    profileViewModel: ProfileViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val onBoardingState by onBoardingViewModel.state.collectAsStateWithLifecycle()
    val homeState by homeViewModel.state.collectAsStateWithLifecycle()
    val productState by productViewModel.state.collectAsStateWithLifecycle()
    val cartState by cartViewModel.state.collectAsStateWithLifecycle()
    val profileState by profileViewModel.state.collectAsStateWithLifecycle()


    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = OnBoarding,
    ) {
        composable<OnBoarding> {
            OnBoardingScreen(navController, onBoardingState)
        }
//        composable<Connection> {
//            ConnectionScreen(navController, connectionViewModel.connectionState)
//        }
        composable<Register> {
            RegisterScreen(navController = navController)
        }
        composable<Login> {
            LoginScreen(navController = navController, viewModel = loginViewModel)
        }
        composable("Home") {
            HomeScreen(homeState){ product ->
                navController.navigate(ProductDetailRoute(
                    product = product
                ))
            }
        }

        composable("Cart") {
            CartScreen(cartState)
        }
        composable<ProductDetailRoute>(
            typeMap = mapOf(
                typeOf<Product>() to HomeNavType.ProductType
            )
        ) {
          val arguments = it.toRoute<ProductDetailRoute>()
          ProductScreen(
              arguments.product,
              productState,
          ){action ->
              productViewModel.onAction(action)
          }
        }
        composable("Admin") {
            AdminDashboardScreen(navController,adminViewModel)
        }

        composable("Search") {
            SearchScreen(searchViewModel)

        }
        composable("Profile") { backStackEntry ->
            ProfileScreen(navController,profileState){ action ->
                profileViewModel.onAction(action)
                    navController.navigate(Login){
                        popUpTo(0)
                    }
            }
        }
        composable("info-profile") { backStackEntry ->
            AccountInformationScreen(profileState,navController)
        }
    }

}


// Todo(good idea to use sealed class)
// Todo make a Graph() that contain all screens in it

enum class Graph{
    Home,
    Profile,
    InfoProfile
}

@Serializable
data class ProductDetailRoute(
    val product: Product
)

@Serializable
object CartDetailRoute

@Serializable
object Login


@Serializable
object Register


@Serializable
object Connection


@Serializable
object OnBoarding

