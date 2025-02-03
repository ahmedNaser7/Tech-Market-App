// Follow this setup guide to integrate the Deno language server with your editor:
// https://deno.land/manual/getting_started/setup_your_environment
// This enables autocomplete, go to definition, etc.

// Setup type definitions for built-in Supabase Runtime APIs
import "jsr:@supabase/functions-js/edge-runtime.d.ts";

console.log("Login Function Initialized!");
console.log("3bnASER");

// Set your Supabase project environment variables
const SUPABASE_URL = Deno.env.get("SUPABASE_URL");
const SUPABASE_KEY = Deno.env.get("SUPABASE_SERVICE_ROLE_KEY");

console.log(SUPABASE_URL);

// Ensure required environment variables are provided
if (!SUPABASE_URL || !SUPABASE_KEY) {
  throw new Error("Missing SUPABASE_URL or SUPABASE_SERVICE_ROLE_KEY in environment variables.");
}

// Define the function handler

Deno.serve(async (req) => { 
  try {
    // Parse the incoming request body
    const { email, password } = await req.json();

    // Validate input
    if (!email || !password) {
      return new Response(
        JSON.stringify({ error: "Email and password are required." }),
        { status: 400, headers: { "Content-Type": "application/json" } }
      );
    }

    console.log("Received email:", email);
    console.log("Received password:", password);

    // Call Supabase Auth API to validate credentials
    const response = await fetch(`${SUPABASE_URL}/auth/v1/token?grant_type=password`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "apikey": SUPABASE_KEY,
        "Authorization": `Bearer ${SUPABASE_KEY}`,
      },
      body: JSON.stringify({ email, password }),
    });

    console.log(response);
    

    // Handle errors from Supabase Auth API
    if (!response.ok) {
      const error = await response.json();
      return new Response(
        JSON.stringify({ error: error.error_description || "Invalid login credentials." }),
        { status: response.status, headers: { "Content-Type": "application/json" } }
      );
    }

    // Extract token and other details from Supabase response
    const data = await response.json();
    return new Response(
      JSON.stringify({ message: "Login successful!", token: data.access_token }),
      { headers: { "Content-Type": "application/json" } }
    );
  } catch (error) {
    console.error("Error handling login request:", error);
    return new Response(
      JSON.stringify({ error: "Internal server error." }),
      { status: 500, headers: { "Content-Type": "application/json" } }
    );
  }
});


/* To invoke locally:

  1. Run `supabase start` (see: https://supabase.com/docs/reference/cli/supabase-start)
  2. Make an HTTP request:

  curl -i --location --request POST 'http://127.0.0.1:54321/functions/v1/login' \
    --header 'Authorization: Bearer YOUR_SUPABASE_SERVICE_ROLE_KEY' \
    --header 'Content-Type: application/json' \
    --data '{"email":"user@example.com","password":"your-password"}'

*/
